package com.project.event_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.event_management.model.Venue;

public interface VenueRepo extends JpaRepository<Venue, Long> {

}
