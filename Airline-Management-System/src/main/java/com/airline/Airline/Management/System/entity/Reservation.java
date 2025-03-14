package com.airline.Airline.Management.System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long flightId;

	private String aadharNumber;
	private String name;
	private String nationality;
	private String address;
	private String gender;
	private String phoneNumber;
	private String email;

	private int seatNumber;

	private LocalDateTime date;
}
