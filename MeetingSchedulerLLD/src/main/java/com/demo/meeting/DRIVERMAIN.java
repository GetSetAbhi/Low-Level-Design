package com.demo.meeting;

import java.time.LocalDateTime;
import java.util.List;

public class DRIVERMAIN {

	public static void main(String[] args) {
		User user1 = new User("Abhishek");

		User user2 = new User("Palak");
		User user3 = new User("Palki");

		MeetingService meetingService = new MeetingService();
		meetingService.createMeeting("Ahlua Meeting", LocalDateTime.of(2025, 10, 13, 12, 02),
				LocalDateTime.of(2025, 10, 13, 12, 05),
				List.of(user2, user3), user1);

		
		meetingService = new MeetingService();
		meetingService.createMeeting("Ahlua Meeting", LocalDateTime.of(2025, 10, 13, 12, 03),
				LocalDateTime.of(2025, 10, 13, 12, 07),
				List.of(user2, user3), user1);

	}

}
