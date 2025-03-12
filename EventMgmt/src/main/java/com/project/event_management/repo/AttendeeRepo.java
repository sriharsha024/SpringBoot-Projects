package com.project.event_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.event_management.model.Attendee;

public interface AttendeeRepo extends JpaRepository<Attendee, Long> {
	
}
