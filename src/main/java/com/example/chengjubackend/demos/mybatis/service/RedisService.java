package com.example.chengjubackend.demos.mybatis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis Service类
 * @author Jilin He
 * @date 2020.01.19
 */

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询
     * @param key 键
     * @param <T> 值
     * @return
     */
    public <T> T get(String key) {
        T obj = null;
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            obj = (T) operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public <T> void set(String key, T obj) {
        set(key, obj, null);
    }

    /**
     * 设置键值，直接覆盖之前映射的值
     * @param key
     * @param obj
     * @param expireTime
     * @param <T>
     */
    public <T> void set(String key, T obj, Long expireTime) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (obj == null) {
            return;
        }
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, obj);
        if (expireTime != null) {
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 移除redis数据库中key-value的数据
     * @param key 键
     */
    public void remove(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

    /**
     * redis数据库中是否包含某个key
     * @param key 键
     * @return 布尔值
     */
    public boolean contains(String key) {
        boolean exists = false;
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        Object obj = get(key);
        if (obj != null) {
            exists = true;
        }
        return exists;
    }
}
