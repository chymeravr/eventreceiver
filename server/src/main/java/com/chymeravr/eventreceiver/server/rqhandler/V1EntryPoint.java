package com.chymeravr.eventreceiver.server.rqhandler;

import com.chymeravr.eventreceiver.server.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.chymeravr.DownstreamLogger;

import javax.servlet.http.HttpServletResponse;

public class V1EntryPoint extends EntryPoint {


    public V1EntryPoint(RequestDeserializer deserializer,
                        ResponseSerializer serializer,
                        DownstreamLogger responseLogger,
                        String topicName) {
        super(deserializer, serializer, responseLogger, topicName);
    }

    public void setReponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}