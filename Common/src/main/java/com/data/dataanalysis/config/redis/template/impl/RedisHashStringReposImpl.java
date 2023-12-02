package com.data.dataanalysis.config.redis.template.impl;

import com.data.dataanalysis.config.redis.template.RedisHashStringRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisHashStringReposImpl implements RedisHashStringRepos {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean exists(String key, String mmsi) {
        return redisTemplate.opsForHash().hasKey(key, mmsi);
    }

    @Override
    public String add(String key, String vessel) {
        redisTemplate.opsForHash().put(key, vessel, "");
        return vessel;
    }

    @Override
    public void add(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public String load(String key, String mmsi) {
        return (String) redisTemplate.opsForHash().get(key, mmsi);
    }

    @Override
    public Map<Object, Object> findAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public String findMany(String key, List<String> hashKeys) {
        List<Object> values = redisTemplate.opsForHash().multiGet(key,new ArrayList<>(hashKeys));

        return values.toString();
    }


    @Override
    public void remove(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }


    @Override
    public void executeAtomicRedisOperations(Runnable redisOperations) {
        redisTemplate.execute(new SessionCallback<Boolean>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                redisOperations.run();
                operations.exec();
                return true;
            }
        });
    }


}
