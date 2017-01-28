package com.chymeravr.eventreceiver.server.rqhandler.iface;

import com.chymeravr.eventreceiver.server.rqhandler.entities.request.EventPing;
import com.chymeravr.processing.eventreceiver.logger.DownstreamLogger;
import com.chymeravr.thrift.eventreceiver.AdServingMeta;
import com.chymeravr.thrift.eventreceiver.EventLog;
import com.chymeravr.thrift.eventreceiver.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TJSONProtocol;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by rubbal on 19/1/17.
 */

@RequiredArgsConstructor
@Slf4j
public abstract class EntryPoint extends AbstractHandler {

    private final RequestDeserializer deserializer;
    private final ResponseSerializer serializer;
    private final DownstreamLogger eventLogger;

    public void handle(String target,
                       org.eclipse.jetty.server.Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        EventPing eventPing = deserializer.deserializeRequest(request);
        setReponseHeaders(response);
        baseRequest.setHandled(true);
        EventPing.AdMetaData adMetaData = eventPing.getAdMetaData();
        System.out.println(eventPing);
        EventLog eventLog = new EventLog(System.currentTimeMillis(),
                eventPing.getAppId(),
                eventPing.getSdkVersion(),
                eventPing.getEventType(),
                new AdServingMeta(adMetaData.getServingId(), adMetaData.getInstanceId()),
                ResponseCode.OK,
                eventPing.getParamMap());
        try {
            eventLogger.sendMessage(adMetaData.getServingId(), encode(new TSerializer(TJSONProtocol::new).serialize(eventLog)));
        } catch (Exception e) {
            System.out.println("Unable to send kafka message");
            e.printStackTrace();
            log.error("Unable to send kafka message");
        }
    }

    private String encode(byte[] binaryData) {
        return new String(binaryData);
    }

    protected abstract void setReponseHeaders(HttpServletResponse response);
}