package com.intuit.gaming.services.Producer;

public interface KafkaMessageProducer {
    public void sendMessageToTopic(String message);
}
