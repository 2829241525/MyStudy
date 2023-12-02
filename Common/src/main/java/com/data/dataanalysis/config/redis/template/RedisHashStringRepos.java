package com.data.dataanalysis.config.redis.template;

import java.util.List;
import java.util.Map;

public interface RedisHashStringRepos {
	
	boolean exists(String KEY, String mmsi);

	String add(String KEY, String vessel);

	void add(String key,  String field, String v);

	void expire(String key, long expire);

	String load(String KEY, String mmsi);
	
	Map<Object, Object> findAll(String KEY);

	String findMany(String key, List<String> hashKeys);
	
	void remove(String KEY, Object... hashkeys);

	void executeAtomicRedisOperations( Runnable redisOperations);
	
}
