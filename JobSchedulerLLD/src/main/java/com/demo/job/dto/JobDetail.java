package com.demo.job.dto;

import java.util.UUID;

import com.demo.job.Job;
import com.demo.job.scheduler.JobSchedule;

public class JobDetail {
	private String jobId;
	private Job job;
	private JobSchedule jobSchedule;
	private JobStatus jobStatus;
	
	public JobDetail(Job job, JobSchedule jobSchedule) {
		this.jobId = UUID.randomUUID().toString();
		this.jobStatus = JobStatus.SCHEDULED;
		this.job = job;
		this.jobSchedule = jobSchedule;
	}
	
	public JobStatus getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getJobId() {
		return jobId;
	}
	public Job getJob() {
		return job;
	}
	public JobSchedule getJobSchedule() {
		return jobSchedule;
	}
	
	
}
