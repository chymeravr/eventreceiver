package com.chymeravr.eventreceiver.rqhandler.iface;

import com.chymeravr.eventreceiver.rqhandler.entities.request.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by rubbal on 19/1/17.
 */
public interface RequestDeserializer {
    Request deserializeRequest(HttpServletRequest request) throws IOException;
}
