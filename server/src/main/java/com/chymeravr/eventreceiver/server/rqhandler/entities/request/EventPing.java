package com.chymeravr.eventreceiver.server.rqhandler.entities.request;

import com.chymeravr.thrift.eventreceiver.EventType;
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
    private final Map<String, String> parameterMap;
    private final AdMeta adMeta;
    public EventPing(long timestamp, int sdkVersion, String appId, String servingId, int instanceId, EventType eventType,
                     Map<String, String> parameterMap) {
        this.timestamp = timestamp;
        this.sdkVersion = sdkVersion;
        this.appId = appId;
        this.parameterMap = parameterMap;
        this.eventType = eventType;
        this.adMeta = new AdMeta(servingId, instanceId);
    }

    @Data
    public class AdMeta {
        private final String servingId;
        private final int instanceId;
    }
}
