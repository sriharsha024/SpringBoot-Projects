package com.project.event_management.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event_management.model.Attendee;
import com.project.event_management.model.AttendeeDTO;
import com.project.event_management.model.Event;
import com.project.event_management.repo.AttendeeRepo;
import com.project.event_management.repo.EventRepo;

@Service
public class AttendeeService {

	@Autowired
	private AttendeeRepo attendeeRepo;

	@Autowired
	private EventRepo eventRepo;

	public List<Attendee> getAllAttendees() {
		return attendeeRepo.findAll();
	}

	public Optional<Attendee> getAttendeeById(long id) {
		return attendeeRepo.findById(id);
	}

	public AttendeeDTO createAttendee(Long eventId, Attendee attendee) {
		Event event = eventRepo.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

		attendee.setEvent(event);
		Attendee savedAttendee = attendeeRepo.save(attendee);
		String eventDate = event.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);

		return new AttendeeDTO(
				savedAttendee.getId(),
				savedAttendee.getName(),
				savedAttendee.getEmail(),
				savedAttendee.getMobileNumber(),
				event.getId(),
				event.getTitle(),
				eventDate
		);
	}

	public Optional<Attendee> updateAttendee(long id, Attendee updatedAttendee) {
		return attendeeRepo.findById(id).map(existingAttendee -> {
			existingAttendee.setName(updatedAttendee.getName());
			existingAttendee.setEmail(updatedAttendee.getEmail());
			existingAttendee.setMobileNumber(updatedAttendee.getMobileNumber());
			return attendeeRepo.save(existingAttendee);
		});
	}

	public boolean deleteAttendee(long id) {
		if (attendeeRepo.existsById(id)) {
			attendeeRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
