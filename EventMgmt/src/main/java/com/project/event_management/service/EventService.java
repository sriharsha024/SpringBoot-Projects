package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event_management.model.Event;
import com.project.event_management.repo.EventRepo;

@Service
public class EventService {

	@Autowired
	private EventRepo eventRepo;

	public List<Event> getAllEvents() {
		return eventRepo.findAll();
	}

	public Optional<Event> getEventById(long id) {
		return eventRepo.findById(id);
	}

	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}

	public Optional<Event> updateEvent(long id, Event eventDetails) {
		return eventRepo.findById(id).map(existingEvent -> {
			eventDetails.setId(id);
			return eventRepo.save(eventDetails);
		});
	}

	public boolean deleteEvent(long id) {
		return eventRepo.findById(id).map(event -> {
			eventRepo.delete(event);
			return true;
		}).orElse(false);
	}
}
