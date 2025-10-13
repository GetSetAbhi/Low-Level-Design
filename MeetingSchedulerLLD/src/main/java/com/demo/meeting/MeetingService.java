package com.demo.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.demo.meeting.Meeting.MeetingBuilder;

public class MeetingService {
	private Map<String, Meeting> meetingMap;
	private ScheduledExecutorService executorService;

	public MeetingService() {
		this.meetingMap = new ConcurrentHashMap<>();
		this.executorService = Executors.newScheduledThreadPool(3);
		this.executorService.scheduleAtFixedRate(() -> sendReminders(), 0, 2, TimeUnit.SECONDS);
		this.executorService.scheduleAtFixedRate(() -> setMeetingStatusToFinished(), 0, 2, TimeUnit.MINUTES);
	}

	public void createMeeting(String meetingTitle, LocalDateTime meetingStartTime, LocalDateTime meetingEndTime,
			List<User> meetingParticipants, User meetingOrganizer) {
		List<User> participants = new ArrayList<>(meetingParticipants);
		participants.add(meetingOrganizer);

		try {
			if (!doMeetingConflictExist(meetingStartTime, meetingEndTime, participants)) {
				Meeting.MeetingBuilder builder = new MeetingBuilder().withMeetingTitle(meetingTitle)
						.withMeetingStartTime(meetingStartTime).withMeetingEndTime(meetingEndTime)
						.withMeetingOrganizer(meetingOrganizer).withMeetingParticipants(meetingParticipants);
				Meeting meeting = builder.createMeeting();
				this.meetingMap.put(meeting.getMeetingId(), meeting);
				meeting.getMeetingOrganizer().meetings.add(meeting);
				for (User user : meeting.getMeetingParticipants()) {
					user.meetings.add(meeting);
				}
			}
		} catch (MeetingConflictException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean doMeetingConflictExist(LocalDateTime meetingStartTime, LocalDateTime meetingEndTime,
			List<User> participants) throws Exception {
		for (User user : participants) {
			Optional<Meeting> meetingOpt = user.meetings.stream()
					.filter(m -> m.getMeetingEndTime().isAfter(meetingStartTime)
							&& m.getMeetingStartTime().isBefore(meetingEndTime))
					.findFirst();
			if (meetingOpt.isPresent()) {
				Meeting meeting = meetingOpt.get();
				String message = user.name + " has a meeting scheduled between " + getMeetingDuration(meeting);
				throw new MeetingConflictException(message);
			}

		}
		return false;
	}
	
	private String getMeetingDuration(Meeting meeting) {
		return getTimeInFormat(meeting.getMeetingStartTime()) + " - " + getTimeInFormat(meeting.getMeetingEndTime());
	}
	
	private String getTimeInFormat(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return formatter.format(time);
	}

	public void sendReminders() {
		List<Meeting> meetings = this.meetingMap.entrySet().stream().map(e -> e.getValue())
				.filter(m -> m.getMeetingStatus().equals(MeetingStatus.SCHEDULED) && isMeetingEligibleForReminder(m))
				.toList();
		for (Meeting meeting : meetings) {
			String message = "The meeting with title {" + meeting.getMeetingTitle() + "} is about to start";
			meeting.sendReminder(message);
		}
	}

	private void setMeetingStatusToFinished() {
		List<Meeting> meetings = this.meetingMap.entrySet().stream().map(e -> e.getValue()).filter(
				m -> m.getMeetingStatus().equals(MeetingStatus.SCHEDULED) && isMeetingEndTimeBeforeCurrentTime(m))
				.toList();
		for (Meeting meeting : meetings) {
			String message = "The meeting with title {" + meeting.getMeetingTitle() + "} has ended";
			meeting.sendReminder(message);
			meeting.setMeetingStatus(MeetingStatus.COMPLETED);
		}
	}

	private boolean isMeetingEndTimeBeforeCurrentTime(Meeting meeting) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime meetingTime = meeting.getMeetingEndTime();
		if (now.equals(meetingTime) || now.isAfter(meetingTime)) {
			return true;
		}
		return false;
	}

	private boolean isMeetingEligibleForReminder(Meeting meeting) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fiveMinsBefore = meeting.getMeetingStartTime().minusMinutes(5);
		if (now.equals(fiveMinsBefore) || now.isAfter(fiveMinsBefore) && now.isBefore(meeting.getMeetingStartTime())) {
			return true;
		}
		return false;
	}
}
