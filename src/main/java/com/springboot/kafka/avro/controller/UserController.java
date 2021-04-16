package com.springboot.kafka.avro.controller;

import com.springboot.kafka.avro.producer.UserProducer;
import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserProducer userProducer;

    @Autowired
    UserController(UserProducer userProducer) {
        this.userProducer = userProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        this.userProducer.sendMessage(new User(name, age));
    }
}