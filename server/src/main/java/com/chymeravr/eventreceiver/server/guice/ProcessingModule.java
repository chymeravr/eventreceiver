package com.chymeravr.eventreceiver.server.guice;

import com.chymeravr.eventreceiver.server.rqhandler.V1EntryPoint;
import com.chymeravr.eventreceiver.server.rqhandler.entities.v1.json.V1RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.entities.v1.json.V1ResponseSerializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.chymeravr.processing.eventreceiver.logger.KafkaLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Created by rubbal on 19/1/17.
 */
public class ProcessingModule extends AbstractModule {
    protected void configure() {

    }


    @Provides
    @Singleton
    RequestDeserializer providesDes() {
        return new V1RequestDeserializer();
    }

    @Provides
    @Singleton
    ResponseSerializer providesSer() {
        return new V1ResponseSerializer();
    }


    @Provides
    @Singleton
        // TODO : Add properties from file
    KafkaLogger providesEventLogger() {
        return new KafkaLogger(null);
    }

    @Provides
    @Singleton
    V1EntryPoint providesV1EntryPoint(RequestDeserializer deserializer,
                                      ResponseSerializer serializer,
                                      KafkaLogger responseLogger) {
        return new V1EntryPoint(deserializer, serializer, responseLogger);
    }
}
