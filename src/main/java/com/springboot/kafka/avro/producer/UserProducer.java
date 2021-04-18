package com.springboot.kafka.avro.producer;

import io.confluent.developer.User;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@CommonsLog(topic = "Producer Logger")
public class UserProducer {

    @Value("${topic.name}")
    private String TOPIC;

    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        final String mensageKey = UUID.randomUUID().toString();
        this.kafkaTemplate.send(TOPIC, mensageKey, user);
        log.info(String.format("Produced user -> %s", user));
    }
}