package com.chymeravr.eventreceiver.server.rqhandler.entities.request;

import com.chymeravr.schemas.eventreceiver.EventType;
import lombok.Data;

import java.util.Map;

/**
 * Created by rubbal on 17/1/17.
 */
@Data
public class EventPing {
    private final long timestamp;
    private final int sdkVersion;
    private final String appId;
    private final EventType eventType;
    private final Map<String, String> paramMap;
    private final AdMetaData adMetaData;
    public EventPing(long timestamp, int sdkVersion, String appId, String servingId, int instanceId, EventType eventType,
                     Map<String, String> paramMap) {
        this.timestamp = timestamp;
        this.sdkVersion = sdkVersion;
        this.appId = appId;
        this.paramMap = paramMap;
        this.eventType = eventType;
        this.adMetaData = new AdMetaData(servingId, instanceId);
    }

    @Data
    public class AdMetaData {
        private final String servingId;
        private final int instanceId;
    }
}
