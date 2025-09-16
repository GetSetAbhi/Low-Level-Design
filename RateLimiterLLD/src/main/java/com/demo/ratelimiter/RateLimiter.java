package com.demo.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

public interface RateLimiter {
	public boolean check();
}

class SlidingWindowImpl implements RateLimiter{
	
	private int maxTokens;
	private Queue<Long> queue;
	private long windowSize;
	
	public SlidingWindowImpl(int maxTokens, long windowSizeInMillis) {
		this.maxTokens = maxTokens;
		this.queue = new LinkedList<>();
		this.windowSize = windowSizeInMillis;
	}
	
	@Override
	public boolean check() {
		long now = System.currentTimeMillis();
		while (!this.queue.isEmpty() && now - queue.peek() > windowSize) {
			queue.poll();
		}
		if (this.queue.size() < maxTokens) {
			queue.add(now);
			return true;
		}
		return false;
	}
	
}

class TokenBucketRateLimiter implements RateLimiter {
	private int maxTokens;
	private double refreshTokensPerSecond;
	private double currentTokenCount;
	private long lastUpdate;
	
	public TokenBucketRateLimiter(int maxTokens, double refreshTokensPerSecond) {
		this.maxTokens = maxTokens;
		this.refreshTokensPerSecond = refreshTokensPerSecond / 1000.0;
		this.currentTokenCount = maxTokens;
		this.lastUpdate = System.currentTimeMillis();
		
	}
	
	@Override
	public synchronized boolean check() {
		refill();
		if (this.currentTokenCount > 0) {
			return true;
		}
		return false;
	}

	private void refill() {
		long currentTime = System.currentTimeMillis();
		double diff = (currentTime - this.lastUpdate);
		if (diff > 0) {
			double newTokens = (diff * this.refreshTokensPerSecond);
			this.currentTokenCount = Math.min(this.currentTokenCount + newTokens, this.maxTokens);
			this.lastUpdate = currentTime;
		}
	}
	
}
