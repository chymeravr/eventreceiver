# Serving

## Request
Path: `/api/v1/events`

#### Request
```json
{
  "sdkVersion": 1,
  "timestamp": 123123,
  "appId": "myapp",
  "eventType": "ERROR/AD_SHOW/AD_CLOSE/AD_CLICK/AD_VIEW_METRICS",
  "adMetaData": {
    "servingId": "uuid1",
    "instanceId": 291
  },
  "paramMap" : {
    "key1": "value1"
    ...
  }
}
```
#### Response
```json
{
    "statusCode": 200,
    "status": "OK"
}
```

#### Execution
* `mvn clean install`
* (Fix the paths in serving.config)`java -jar assembly/target/*-jar-with-dependencies.jar -c <config> file -p 8080`

