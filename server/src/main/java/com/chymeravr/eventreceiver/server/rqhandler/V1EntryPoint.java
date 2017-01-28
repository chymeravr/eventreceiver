package com.chymeravr.eventreceiver.server.rqhandler;

import com.chymeravr.eventreceiver.server.rqhandler.iface.EntryPoint;
import com.chymeravr.eventreceiver.server.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.chymeravr.processing.eventreceiver.logger.DownstreamLogger;

import javax.servlet.http.HttpServletResponse;

public class V1EntryPoint extends EntryPoint {


    public V1EntryPoint(RequestDeserializer deserializer, ResponseSerializer serializer,
                        DownstreamLogger responseLogger) {
        super(deserializer, serializer, responseLogger);
    }

    public void setReponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}