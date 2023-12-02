package com.data.dataanalysis.config.redis.template.impl;

import com.data.dataanalysis.config.redis.template.RedisRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisReposImpl implements RedisRepos {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private ValueOperations<String, Object>  valueOperations;

	@PostConstruct
	private void init() {
		redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
		valueOperations = redisTemplate.opsForValue();
	}
	
	public void set(String key,final Object value,Long timeout) {
		valueOperations.set(key, value, timeout,TimeUnit.SECONDS);
	}
	
	public Object get(String key) {
		return valueOperations.get(key);
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	public void deleteByPrefix(String prefix) {
		Set<String> keys = redisTemplate.keys(prefix + "*");
		if (keys != null && !keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}
}
