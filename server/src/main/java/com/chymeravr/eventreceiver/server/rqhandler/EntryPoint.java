package com.chymeravr.eventreceiver.server.rqhandler;

import com.chymeravr.DownstreamLogger;
import com.chymeravr.eventreceiver.server.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.chymeravr.schemas.eventreceiver.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TSerializer;
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
    private final String topicName;

    public void handle(String target,
                       org.eclipse.jetty.server.Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        EventPing eventPing = deserializer.deserializeRequest(request);
        setReponseHeaders(response);
        baseRequest.setHandled(true);

        for (SDKEvent event : eventPing.getEvents()) {
            RuntimeAdMeta adMeta = event.getAdMeta();
            EventLog eventLog = new EventLog(event.getTimestamp(),
                    eventPing.getAppId(),
                    eventPing.getSdkVersion(),
                    event.getEventType(),
                    new AdServingMeta(adMeta.getServingId(), adMeta.getInstanceId()),
                    ResponseCode.OK,
                    event.getParamsMap());
            try {
                eventLogger.sendMessage(adMeta.getServingId(),
                        encode(new TSerializer().serialize(eventLog)),
                        topicName);
            } catch (Exception e) {
                log.error("Unable to send kafka message");
            }
        }
    }

    private String encode(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }

    protected abstract void setReponseHeaders(HttpServletResponse response);
}