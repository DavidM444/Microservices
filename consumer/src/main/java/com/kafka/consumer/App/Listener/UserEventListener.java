package com.kafka.consumer.App.Listener;

import com.kafka.consumer.App.Controller.UserService;
import kafka.producer.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {

    private final UserService userService;

    @Autowired
    public UserEventListener(UserService service){
        this.userService = service;
    }

    @KafkaListener(topics = "save-user", groupId = "user-register")
    public void userListener(User user){
        System.out.println("recibiendo evento "+ user.getNombre());
        userService.serviceSaveUser(user);
    }
}
