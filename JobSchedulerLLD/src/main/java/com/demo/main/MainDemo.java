package com.demo.main;

import com.demo.job.Job;
import com.demo.job.scheduler.OneTimeSchedule;
import com.demo.job.scheduler.RecurringJobSchedule;
import com.demo.job.service.JobService;

public class MainDemo {

	public static void main(String[] args) {
		JobService service = new JobService();
		
		service.addJob(new Job() {
			@Override
			public void execute() {
				System.out.println("First Job");
				
			}
		}, new RecurringJobSchedule(30000));
		
		long time = System.currentTimeMillis() + 1*60*1000;
		service.addJob(new Job() {
			@Override
			public void execute() {
				System.out.println("Second Job");
				
			}
		}, new OneTimeSchedule(time));

	}

}
