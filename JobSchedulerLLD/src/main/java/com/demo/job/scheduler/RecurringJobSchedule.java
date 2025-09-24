package com.demo.job.scheduler;

public class RecurringJobSchedule extends JobSchedule {
	
	private long runIntervalInMillis;
	private long lastRunTimeInMillis;
	
	public RecurringJobSchedule(long runIntervalInMillis) {
		this.runIntervalInMillis = runIntervalInMillis;
		this.lastRunTimeInMillis = 0;
	}

	@Override
	public boolean shouldRun(long currentTimeInMillis) {
		if (currentTimeInMillis - this.lastRunTimeInMillis >= this.runIntervalInMillis) {
			this.lastRunTimeInMillis = currentTimeInMillis;
			return true;
		}
		return false;
	}

}
