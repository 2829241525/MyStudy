package com.data.dataanalysis.config.redis.template;

public interface RedisRepos {
	
	void set(String key,Object value,Long timeout);
	
	Object get(String key);

	void delete(String key);

	boolean exists(String key);

	void deleteByPrefix(String prefix);
}
