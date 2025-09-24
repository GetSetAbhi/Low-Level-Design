package com.demo.job.scheduler;

public class OneTimeSchedule extends JobSchedule {

	private long scheduledTime;
	
	public OneTimeSchedule(long scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	@Override
	public boolean shouldRun(long currentTimeInMillis) {
		return currentTimeInMillis >= this.scheduledTime;
	}

}
