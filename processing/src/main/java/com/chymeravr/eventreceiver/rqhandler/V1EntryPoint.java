package com.chymeravr.eventreceiver.rqhandler;

import com.chymeravr.eventreceiver.adfetcher.AdFetcher;
import com.chymeravr.eventreceiver.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.rqhandler.iface.ResponseSerializer;
import com.chymeravr.eventreceiver.logger.ResponseLogger;
import com.chymeravr.eventreceiver.rqhandler.iface.EntryPoint;

import javax.servlet.http.HttpServletResponse;

public class V1EntryPoint extends EntryPoint {


    public V1EntryPoint(RequestDeserializer deserializer, ResponseSerializer serializer, AdFetcher adFetcher,
                        ResponseLogger responseLogger) {
        super(deserializer, serializer, adFetcher, responseLogger);
    }

    public void setReponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}