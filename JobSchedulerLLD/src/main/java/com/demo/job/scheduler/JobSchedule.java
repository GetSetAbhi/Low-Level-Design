package com.demo.job.scheduler;

public abstract class JobSchedule {
	public abstract boolean shouldRun(long currentTimeInMillis);
	public abstract void markExecuted(long currentTimeInMillis);
	public abstract boolean isCompleted();
}
