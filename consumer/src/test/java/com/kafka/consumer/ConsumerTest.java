package com.kafka.consumer;

import com.kafka.consumer.App.Listener.UserEventListener;
import kafka.producer.Entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.Time;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ConsumerTest {

    @Container
    private static final KafkaContainer container = new KafkaContainer(DockerImageName.parse(
            "confluentinc/cp-kafka:latest"
    ));

    @Autowired
    public KafkaTemplate<String, Object> template;

    @DynamicPropertySource
    public static void loadServer(DynamicPropertyRegistry registry){
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
    }

    @Test
    void TestConsume(){
        System.out.println("test consumer lister");
        template.send("save-user", new User(UUID.randomUUID(),"Juan ", "Perez ", "23"));
        System.out.println("consume complete");

        //for dont close inmedialty: warn Producer is closed focefully. We need some time to server start
        //wait for message consumption
        await().pollInterval(Duration.ofSeconds(4)).atMost(10, TimeUnit.SECONDS).untilAsserted(()->{
        });

    }
}
