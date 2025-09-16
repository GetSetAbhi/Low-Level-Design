package com.demo.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
	
	private Map<String, RateLimiter> map;
	
	public RateLimiterService() {
		this.map = new HashMap<>();
	}
	
	public void registerRateLimiter(String userId, RateLimiter rateLimiter) {
		this.map.put(userId, rateLimiter);
	}
	
	public boolean check(String userId) {
		RateLimiter limiter = this.map.get(userId);
		return limiter.check();
	}

}
