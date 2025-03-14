package com.airline.Airline.Management.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.Management.System.Repo.FlightRepo;
import com.airline.Airline.Management.System.entity.Flight;

@Service
public class FlightService {

    @Autowired
    private FlightRepo flightRepo;

    public Optional<Flight> getFlightById(Long id) {
        return flightRepo.findById(id);
    }

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
        return flightRepo.findByOriginAndDestination(origin, destination);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));

        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setFlightName(flightDetails.getFlightName());
        flight.setOrigin(flightDetails.getOrigin());
        flight.setDestination(flightDetails.getDestination());
        flight.setAirline(flightDetails.getAirline());
        flight.setAvailableSeats(flightDetails.getAvailableSeats());

        return flightRepo.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }

    public void updateSeatsOnReservation(Long flightId) {
        Flight flight = flightRepo.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        if (flight.getAvailableSeats() > 0) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepo.save(flight);
        } else {
            throw new RuntimeException("No seats available for this flight");
        }
    }

    public void updateSeatsOnCancel(Long flightId) {
        Flight flight = flightRepo.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        flightRepo.save(flight);
    }
}
