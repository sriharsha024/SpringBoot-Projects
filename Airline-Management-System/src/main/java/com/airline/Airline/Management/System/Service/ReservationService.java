package com.airline.Airline.Management.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.Management.System.Repo.ReservationRepo;
import com.airline.Airline.Management.System.entity.Reservation;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private FlightService flightService;

    public Reservation saveReservation(Reservation reservation) {
        flightService.updateSeatsOnReservation(reservation.getFlightId());
        return reservationRepo.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> existingReservationOpt = reservationRepo.findById(id);

        if (existingReservationOpt.isPresent()) {
            Reservation existingReservation = existingReservationOpt.get();

            // If flight ID is changing, adjust seat counts accordingly
            if (!existingReservation.getFlightId().equals(updatedReservation.getFlightId())) {
                flightService.updateSeatsOnCancel(existingReservation.getFlightId());
                flightService.updateSeatsOnReservation(updatedReservation.getFlightId());
            }

            // Update reservation details
            existingReservation.setCustomerId(updatedReservation.getCustomerId());
            existingReservation.setFlightId(updatedReservation.getFlightId());
            existingReservation.setDate(updatedReservation.getDate());
            existingReservation.setSeatNumber(updatedReservation.getSeatNumber());

            return reservationRepo.save(existingReservation);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepo.findById(id);
    }

    public List<Reservation> getReservationsByCustomerId(Long customerId) {
        return reservationRepo.findByCustomerId(customerId);
    }

    public List<Reservation> getReservationsByFlightId(Long flightId) {
        return reservationRepo.findByFlightId(flightId);
    }

    public Reservation getReservationByCustomerAndFlight(Long customerId, Long flightId) {
        return reservationRepo.findByCustomerIdAndFlightId(customerId, flightId);
    }

    public void deleteReservation(Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        if (reservation.isPresent()) {
            flightService.updateSeatsOnCancel(reservation.get().getFlightId());
            reservationRepo.deleteById(id);
        }
    }
}
