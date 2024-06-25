package com.kafka.producer;

import com.kafka.producer.Config.UserController;
import com.kafka.producer.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers //this class will be container
public class ExampleProducerTest {

    private User user;

    @Autowired
    private UserController userController;
    //specify the container
    @Container
    private static  KafkaContainer container = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));


    @DynamicPropertySource //load container properties
    public static void loadServer(DynamicPropertyRegistry registry){
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
    }


    @BeforeEach
    public void Before(){
        user = new User(UUID.randomUUID(),"Adriana Valdez","adriana@gmail.com", "23");
    }

    @Test
    public void emitEvent(){
        userController.produceEventNewUser(user);
        await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(()->{});

    }
}
