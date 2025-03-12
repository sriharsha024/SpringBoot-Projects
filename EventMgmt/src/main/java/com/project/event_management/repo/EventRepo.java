package com.project.event_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.event_management.model.Event;

public interface EventRepo extends JpaRepository<Event, Long> {

}
