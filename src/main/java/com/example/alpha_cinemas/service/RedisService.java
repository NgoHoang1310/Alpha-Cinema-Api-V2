package com.example.alpha_cinemas.service;

import java.util.Map;

public interface RedisService {
    void save(Object value, String id);
    Object findById(String id);
    Map<String, Object> findAllKeysAndValues();
    String getByKey(String key);
    void deleteById(String id);
}
