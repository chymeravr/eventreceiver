package com.chymeravr.processing.eventreceiver.logger;

/**
 * Created by rubbal on 28/1/17.
 */
public interface DownstreamLogger {
    void sendMessage(String key, String value);
}
