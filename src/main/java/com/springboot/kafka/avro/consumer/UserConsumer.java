package com.springboot.kafka.avro.consumer;

import io.confluent.developer.User;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog(topic = "Consumer Logger")
public class UserConsumer {

    @Value("${topic.name}")
    private String topicName;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String user,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List topics,
                               @Header(KafkaHeaders.OFFSET) List offsets) {
        System.out.printf("Topic::: %s  partitions---%d[ offsets::%d] \"user::::%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), user);
    }
//    public void consume(ConsumerRecord<String, User> record) {
//        log.info(String.format("Consumed message -> %s", record.value()));
//    }
}