package com.airline.Airline.Management.System.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.Airline.Management.System.entity.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    // Custom query method to find reservations by customer ID
    List<Reservation> findByCustomerId(Long customerId);

    // Custom query method to find reservations by flight ID
    List<Reservation> findByFlightId(Long flightId);

    // Custom query method to find reservations by customer ID and flight ID
    Reservation findByCustomerIdAndFlightId(Long customerId, Long flightId);
}
