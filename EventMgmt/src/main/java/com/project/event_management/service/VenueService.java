package com.project.event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event_management.model.Venue;
import com.project.event_management.repo.VenueRepo;

@Service
public class VenueService {
	@Autowired
    private VenueRepo venueRepo;

    public List<Venue> getAllVenues() {
        return venueRepo.findAll();
    }

    public Venue getVenueById(long id) {
        return venueRepo.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public Venue createVenue(Venue venue) {
        return venueRepo.save(venue);
    }

    public Venue updateVenue(long id, Venue venueDetails) {
        Venue existingVenue=venueRepo.findById(id).orElseThrow(() -> new RuntimeException("Venue details cannot be null"));
        existingVenue.setName(venueDetails.getName());
        existingVenue.setLocation(venueDetails.getLocation());
        
        return venueRepo.save(existingVenue);
    }

    public void deleteVenue(long id) {
        Venue exists = venueRepo.findById(id).orElseThrow(() -> new RuntimeException("Venue not found"));
        venueRepo.delete(exists);
    }
}

