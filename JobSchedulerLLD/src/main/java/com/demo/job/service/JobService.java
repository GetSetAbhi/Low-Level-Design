package com.demo.job.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.demo.job.Job;
import com.demo.job.dto.JobDetail;
import com.demo.job.dto.JobStatus;
import com.demo.job.scheduler.JobSchedule;

public class JobService {

	private ScheduledExecutorService executorService;
	private Map<String, JobDetail> jobMap;

	public JobService() {
		this.executorService = Executors.newScheduledThreadPool(3);
		this.jobMap = new ConcurrentHashMap<>();
		this.executorService.scheduleAtFixedRate(this::worker, 0, 2, TimeUnit.SECONDS);
	}

	public void addJob(Job job, JobSchedule jobSchedule) {
		JobDetail detail = new JobDetail(job, jobSchedule);
		this.jobMap.put(detail.getJobId(), detail);
	}

	private void worker() {
		long currentTimeInMillis = System.currentTimeMillis();
		List<JobDetail> upcomingJobs = this.jobMap.entrySet().stream().map(e -> e.getValue())
				.filter(j -> j.getJobStatus().equals(JobStatus.SCHEDULED)).toList();
		for (JobDetail jobDetail : upcomingJobs) {
			if (jobDetail.getJobSchedule().shouldRun(currentTimeInMillis)) {
				this.executorService.execute(() -> jobRunner(jobDetail, currentTimeInMillis));
			}
		}
	}

	private void jobRunner(JobDetail detail, long currentTimeInMillis) {
		detail.setJobStatus(JobStatus.RUNNING);
		try {
			System.out.println("Running Job : " + detail.getJobId());
			detail.getJob().execute();
			detail.getJobSchedule().markExecuted(currentTimeInMillis);
		} catch (Exception e) {
			System.err.println("Error running job: " + detail.getJobId());
		} finally {
			if (detail.getJobSchedule().isCompleted()) {
				detail.setJobStatus(JobStatus.FINISHED);
			} else {
				detail.setJobStatus(JobStatus.SCHEDULED);
			}
		}
	}
}
