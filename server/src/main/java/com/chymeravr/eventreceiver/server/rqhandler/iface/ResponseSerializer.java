package com.chymeravr.eventreceiver.server.rqhandler.iface;

import com.chymeravr.eventreceiver.server.rqhandler.entities.response.AdResponse;

/**
 * Created by rubbal on 19/1/17.
 */
public interface ResponseSerializer {
    byte[] serialize(AdResponse response);
}
