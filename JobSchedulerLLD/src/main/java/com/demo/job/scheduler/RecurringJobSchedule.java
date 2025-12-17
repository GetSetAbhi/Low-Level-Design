package com.demo.job.scheduler;

public class RecurringJobSchedule extends JobSchedule {
	
	private long runIntervalInMillis;
	private long lastRunTimeInMillis;
	private boolean isExecuted = false;
	
	public RecurringJobSchedule(long runIntervalInMillis) {
		this.runIntervalInMillis = runIntervalInMillis;
		this.lastRunTimeInMillis = 0;
	}

	@Override
	public boolean shouldRun(long currentTimeInMillis) {
		if (currentTimeInMillis - this.lastRunTimeInMillis >= this.runIntervalInMillis) {
			return true;
		}
		return false;
	}

	@Override
	public void markExecuted(long currentTimeInMillis) {
		this.lastRunTimeInMillis = currentTimeInMillis;
		this.isExecuted = false;
	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return this.isExecuted;
	}

}
