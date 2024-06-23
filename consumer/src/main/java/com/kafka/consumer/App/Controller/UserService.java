package com.kafka.consumer.App.Controller;

import kafka.producer.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public void serviceSaveUser(User user){
        userRepository.saveUserRepository(user);
    }
}
