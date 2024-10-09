package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Object token, String userId){
        System.out.println(userId);
        redisTemplate.opsForValue().set(userId,token);
    }

    @Override
    public Object findById(String id){
        return redisTemplate.opsForValue().get(id);
    }

    public Map<String, Object> findAllKeysAndValues() {
        Map<String, Object> allRecords = new HashMap<>();
        // Lấy tất cả các keys trong Redis
        Set<String> keys = redisTemplate.keys("16");
        System.out.println(keys);
        if (keys != null) {
            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);
                allRecords.put(key, value);
            }
        }
        return allRecords;
    }

    @Override
    public String getByKey(String key){
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }

    public void deleteById(String id){
        redisTemplate.opsForValue().getAndDelete(id);
    }
}
