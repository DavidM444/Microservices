package com.kafka.consumer.App.Controller;

import com.kafka.consumer.App.JedisConfig;
import kafka.producer.Entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class UserRepository {
    public static  final String key= "user:";
    RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();


    public void saveUserRepository(User user){
        Jedis jedis = JedisConfig.getJedisConnection();
        jedis.set(key + user.getId(), user.getNombre());
    }
}
