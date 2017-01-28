package com.chymeravr.eventreceiver.server.rqhandler.entities.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by rubbal on 19/1/17.
 */
@Data
public class AdResponse {
    private final int statusCode;
    private final String status;
    private final List<Integer> experimentId;
    private final Map<String, ResponseObjects.AdMeta> ads;
}
