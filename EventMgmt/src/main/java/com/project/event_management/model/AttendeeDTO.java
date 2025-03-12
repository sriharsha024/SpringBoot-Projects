package com.project.event_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeDTO {

	private long id;
	private String name;
	private String email;
	private String mobileNumber;
	private long eventId;
	private String eventTitle;
	private String eventDate;
}
