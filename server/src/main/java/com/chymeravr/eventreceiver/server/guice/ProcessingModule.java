package com.chymeravr.eventreceiver.server.guice;

import com.chymeravr.KafkaLogger;
import com.chymeravr.eventreceiver.server.rqhandler.V1EntryPoint;
import com.chymeravr.eventreceiver.server.rqhandler.entities.v1.json.V1RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.entities.v1.json.V1ResponseSerializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.RequestDeserializer;
import com.chymeravr.eventreceiver.server.rqhandler.iface.ResponseSerializer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.Data;
import org.apache.commons.configuration.Configuration;

/**
 * Created by rubbal on 19/1/17.
 */
@Data
public class ProcessingModule extends AbstractModule {
    private final Configuration configuration;

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
    KafkaLogger providesEventLogger() {
        return new KafkaLogger(configuration.subset("kafka"));
    }

    @Provides
    @Singleton
    V1EntryPoint providesV1EntryPoint(RequestDeserializer deserializer,
                                      ResponseSerializer serializer,
                                      KafkaLogger responseLogger) {
        return new V1EntryPoint(deserializer, serializer, responseLogger, configuration.getString("kafkaTopicName"));
    }
}
