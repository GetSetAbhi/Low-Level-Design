package com.demo.job.scheduler;

public class OneTimeSchedule extends JobSchedule {

	private long scheduledTime;
	private boolean isExecuted = false;
	
	public OneTimeSchedule(long scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	@Override
	public boolean shouldRun(long currentTimeInMillis) {
		return currentTimeInMillis >= this.scheduledTime;
	}

	@Override
	public void markExecuted(long currentTimeInMillis) {
		this.isExecuted = true;
	}

	@Override
	public boolean isCompleted() {
		return this.isExecuted;
	}
}
