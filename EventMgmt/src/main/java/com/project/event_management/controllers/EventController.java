package com.project.event_management.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.event_management.model.Event;
import com.project.event_management.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable long id) {
		return eventService.getEventById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Event> createEvent(@Valid @RequestBody Event eventDetails) {
		Event createdEvent = eventService.createEvent(eventDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable long id, @Valid @RequestBody Event eventDetails) {
		Optional<Event> updatedEvent = eventService.updateEvent(id, eventDetails);
		return updatedEvent
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
		boolean deleted = eventService.deleteEvent(id);
		return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
