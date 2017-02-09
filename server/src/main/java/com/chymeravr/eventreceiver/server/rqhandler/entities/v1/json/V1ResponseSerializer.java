package com.chymeravr.eventreceiver.server.rqhandler.entities.v1.json;

import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.chymeravr.schemas.eventreceiver.EventResponse;
import com.google.gson.Gson;

/**
 * Created by rubbal on 19/1/17.
 */
public class V1ResponseSerializer implements ResponseSerializer {

    @Override
    public byte[] serialize(EventResponse response) {
        return new Gson().toJson(response).getBytes();
    }
}
