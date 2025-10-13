package com.demo.meeting;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Meeting {

	private String meetingId;
	private String meetingTitle;
	private LocalDateTime meetingStartTime;
	private LocalDateTime meetingEndTime;
	private List<User> meetingParticipants;
	private User meetingOrganizer;
	private MeetingStatus meetingStatus;

	public Meeting() {
		this.meetingId = UUID.randomUUID().toString();
		this.meetingStatus = MeetingStatus.SCHEDULED;
	}

	public void sendReminder(String message) {
		for (User user : meetingParticipants) {
			user.receiveMessage(message);
		}
		this.meetingOrganizer.receiveMessage(message);
	}

	public MeetingStatus getMeetingStatus() {
		return meetingStatus;
	}

	public void setMeetingStatus(MeetingStatus meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public LocalDateTime getMeetingStartTime() {
		return meetingStartTime;
	}

	public LocalDateTime getMeetingEndTime() {
		return meetingEndTime;
	}

	public List<User> getMeetingParticipants() {
		return meetingParticipants;
	}

	public User getMeetingOrganizer() {
		return meetingOrganizer;
	}

	public static class MeetingBuilder {
		private Meeting meeting;

		private String meetingTitle;
		private LocalDateTime meetingStartTime;
		private LocalDateTime meetingEndTime;
		private List<User> meetingParticipants;
		private User meetingOrganizer;

		public MeetingBuilder() {
			meeting = new Meeting();
		}

		public MeetingBuilder withMeetingTitle(String title) {
			meetingTitle = title;
			return this;
		}

		public MeetingBuilder withMeetingOrganizer(User user) {
			meetingOrganizer = user;
			return this;
		}

		public MeetingBuilder withMeetingStartTime(LocalDateTime time) {
			meetingStartTime = time;
			return this;
		}

		public MeetingBuilder withMeetingEndTime(LocalDateTime time) {
			meetingEndTime = time;
			return this;
		}

		public MeetingBuilder withMeetingParticipants(List<User> users) {
			meetingParticipants = users;
			return this;
		}

		public Meeting createMeeting() {
			meeting.meetingOrganizer = this.meetingOrganizer;
			meeting.meetingStartTime = this.meetingStartTime;
			meeting.meetingEndTime = this.meetingEndTime;
			meeting.meetingParticipants = this.meetingParticipants;
			meeting.meetingTitle = this.meetingTitle;
			return meeting;
		}
	}
}
