namespace java com.chymeravr.thrift.eventreceiver

enum ResponseCode {
    OK,
    ERROR
}

enum EventType {
    ERROR,
    AD_SHOW,
    AD_CLOSE,
    AD_CLICK,
    AD_VIEW_METRICS
}

struct AdServingMeta {
    1: required string servingId;
    2: required i32 instanceId;
}

struct EventLog {
    1: required i64 timestamp;
    2: required string appId;
    3: required i32 sdkVersion;
    4: required EventType eventType;
    5: required AdServingMeta adServingMeta;
    6: required ResponseCode responseCode;
    7: required map<string, string> parameterMap;
}