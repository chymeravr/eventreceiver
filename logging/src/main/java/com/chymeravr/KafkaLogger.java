package com.chymeravr;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by rubbal on 19/1/17.
 */
@Slf4j
public class KafkaLogger implements DownstreamLogger {
    private final Producer<String, String> producer;

    public KafkaLogger(Configuration configuration) {
        Properties props = new Properties();
        addToKafkaProps(props, ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration);
        addToKafkaProps(props, ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, configuration);
        addToKafkaProps(props, ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, configuration);
        addToKafkaProps(props, ProducerConfig.MAX_BLOCK_MS_CONFIG, configuration);
        addToKafkaProps(props, ProducerConfig.ACKS_CONFIG, configuration);

        this.producer = new KafkaProducer<>(props);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // TODO : Find out if close method flushes before close
            producer.flush();
            producer.close();
        }));
    }

    private void addToKafkaProps(Properties properties, String key, Configuration configuration) {
        properties.put(key, configuration.getString(key));
    }

    public void sendMessage(String requestId, String event, String topicName) {
        final ProducerRecord<String, String> data = new ProducerRecord<>(topicName, requestId, event);
        producer.send(data, (metadata, exception) -> {
            // TODO : spool locally
            if (exception != null) {
                log.error("Unable to send kafka message : {}", data, exception);
            }
        });
    }
}
