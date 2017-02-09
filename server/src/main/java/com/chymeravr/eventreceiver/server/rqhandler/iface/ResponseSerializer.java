package com.chymeravr.eventreceiver.server.rqhandler.iface;


import com.chymeravr.schemas.eventreceiver.EventResponse;

/**
 * Created by rubbal on 19/1/17.
 */
public interface ResponseSerializer {
    byte[] serialize(EventResponse response);
}
