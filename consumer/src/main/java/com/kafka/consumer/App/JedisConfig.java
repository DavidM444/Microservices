package com.kafka.consumer.App;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisConfig {
    private static final String HOST = "localhost";
    private static  final int PORT = 6379;
    Jedis jedis;

    //don't use connection pool defined
    public static Jedis getJedisConnection(){
        return new Jedis(HOST, PORT);
    }

    //test connection
    public static void main(String[] args) {

        Jedis jedis = JedisConfig.getJedisConnection();
        jedis.set("carro", "mazda");
        System.out.println(jedis.get("carro"));
    }
}
