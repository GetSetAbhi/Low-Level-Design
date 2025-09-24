package com.demo.job.scheduler;

public abstract class JobSchedule {
	public abstract boolean shouldRun(long currentTimeInMillis);
}
