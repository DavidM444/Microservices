package com.kafka.producer.Config;

import com.kafka.producer.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    private final KafkaTemplate<String, User> template;

    //inject kafka template
    @Autowired
    public UserController(KafkaTemplate<String, User> tmp){
        this.template = tmp;
    }

    @PostMapping
    public ResponseEntity<String> produceAndSaveUser(@RequestBody User user){
        try{
            System.out.println("sending event");
            template.send("save-user", user);
        }catch (KafkaProducerException ex){
            System.out.println("Exception "+ ex.getMessage());
        }
        return  ResponseEntity.ok("User register, and emitted event");

    }
}
