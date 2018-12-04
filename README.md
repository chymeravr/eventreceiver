
# Event Receiver
VR devices send constant logs of user actions to Event Receiver. It collects all those logs and puts it into kafka queue.

## Usage
### Request

Path: `/api/v1/events`

  

#### Request

```json

{

"sdkVersion": 4,

"timestamp": 123123,

"appId": "myapp111",

"events": [

{

"timestamp": 555555,

"eventType": "AD_SHOW",

"adMeta": {

"servingId": "c049593d-5dc8-4a6e-854e-16857dbe8baf",

"instanceId": 291

},

"paramsMap" : {

"key1": "value1",

"key2": "value2"

},

"asdfasd": 123

},

{

"timestamp": 66666,

"eventType": "AD_SHOW",

"adMeta": {

"servingId": "c049593d-5dc8-4a6e-854e-16857dbe8baf",

"instanceId": 291

},

"paramsMap" : {

"key1": "value1",

"key2": "value2"

}

}

]

}

```

#### Response

```json

{

}

```

  

#### Execution

*  `mvn clean install`

* (Fix the paths in serving.config)`java -jar assembly/target/*-jar-with-dependencies.jar -c <config> file -p 8080`

