package com.data.dataanalysis.config.redis.template;

import java.util.Set;

public interface RedisSetRepos {
	
	void push(String KEY, String hash);

	Set<String> pop(String KEY, int size);
	
	long size(String KEY);
}
