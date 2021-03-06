package com.chymeravr.eventreceiver.server.rqhandler.iface;


import com.chymeravr.schemas.eventreceiver.EventPing;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by rubbal on 19/1/17.
 */
public interface RequestDeserializer {
    EventPing deserializeRequest(HttpServletRequest request) throws IOException;
}
