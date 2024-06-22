package com.kafka.producer.Entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class User {

    private UUID id;
    private String nombre;
    private String email;
    private String edad;
}
