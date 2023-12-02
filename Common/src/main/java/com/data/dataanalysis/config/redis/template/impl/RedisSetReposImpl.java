package com.data.dataanalysis.config.redis.template.impl;

import com.data.dataanalysis.config.redis.template.RedisSetRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Repository
public class RedisSetReposImpl implements RedisSetRepos {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private ZSetOperations<String, String> setOperations;

	@PostConstruct
	private void init() {
		setOperations = redisTemplate.opsForZSet();
	}

	public void push(String KEY, final String hash) {

		double lastScore = getLastScore(KEY);
		
		setOperations.add(KEY, hash, lastScore+1);
	}

	private double getLastScore(String KEY) {
		Set<String> result = setOperations.reverseRange(KEY, 0, 0);
		if(result == null || result.size()<1) {
			return 0.0;
		}
		Object last = result.iterator().next();
		return setOperations.score(KEY, last);
	}
	
	public Set<String> pop(String KEY, int size) {
		Set<String> result = setOperations.range(KEY, 0, size-1);
		
		setOperations.remove(KEY, result.toArray());
		return result;
	}

	@Override
	public long size(String KEY) {
		return setOperations.size(KEY);
	}
}
