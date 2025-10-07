package com.demo.ratelimiter;

public class TokenCreditRateLimiter implements RateLimiter {

	private int maxTokens;
	private int maxCredits;
	private double refreshTokensPerSecond;
	private int currentTokenCount;
	private int currentCredits;
	private long lastUpdate;
	private Object lock;

	public TokenCreditRateLimiter(int maxTokens, int maxCredits, double refreshTokensPerSecond) {
		this.maxTokens = maxTokens;
		this.refreshTokensPerSecond = refreshTokensPerSecond / 1000.0;
		this.currentTokenCount = maxTokens;
		this.lastUpdate = System.currentTimeMillis();
		this.currentCredits = 0;
		this.lock = new Object();

	}

	@Override
	public boolean check() {
		synchronized (lock) {
			refill();
			if (this.currentTokenCount > 0) {
				this.currentTokenCount -= 1;
				return true;
			} else if (this.currentCredits > 0) {
				this.currentCredits -= 1;
				return true;
			}
			return false;
		}
	}

	private void refill() {
		long currentTime = System.currentTimeMillis();
		double diff = (currentTime - this.lastUpdate);
		if (diff > 0) {
			int newTokens = (int) (diff * this.refreshTokensPerSecond);
			this.currentTokenCount += newTokens;

			if (this.currentTokenCount > this.maxTokens) {
				int creditsEarned = this.currentTokenCount - this.maxTokens;
				this.currentCredits = Math.min(creditsEarned + this.currentCredits, this.maxCredits);
				this.currentTokenCount = this.maxTokens;
			}
			this.lastUpdate = currentTime;
		}
	}

}
