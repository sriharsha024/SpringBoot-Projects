package com.project.event_management.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.event_management.model.Attendee;
import com.project.event_management.model.AttendeeDTO;
import com.project.event_management.service.AttendeeService;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

	@Autowired
	private AttendeeService attendeeService;

	@GetMapping
	public ResponseEntity<List<Attendee>> getAllAttendees() {
		List<Attendee> attendees = attendeeService.getAllAttendees();
		return ResponseEntity.ok(attendees);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Attendee> getAttendeeById(@PathVariable long id) {
		return attendeeService.getAttendeeById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/event/{eventId}")
	public ResponseEntity<?> createAttendee(@PathVariable long eventId, @RequestBody Attendee attendee) {
		try {
			AttendeeDTO createdAttendee = attendeeService.createAttendee(eventId, attendee);
			return ResponseEntity.ok(createdAttendee);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Attendee> updateAttendee(@PathVariable long id, @RequestBody Attendee updatedAttendee) {
		Optional<Attendee> attendee = attendeeService.updateAttendee(id, updatedAttendee);
		return attendee.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAttendee(@PathVariable long id) {
		boolean deleted = attendeeService.deleteAttendee(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
