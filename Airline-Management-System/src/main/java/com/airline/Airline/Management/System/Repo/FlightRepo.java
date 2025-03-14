package com.airline.Airline.Management.System.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.Airline.Management.System.entity.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> {
    // Custom query method to find a flight by flight number
    Flight findByFlightNumber(String flightNumber);

    // Custom query method to find flights based on origin and destination
    List<Flight> findByOriginAndDestination(String origin, String destination);
}
