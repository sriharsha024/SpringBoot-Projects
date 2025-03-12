package com.project.event_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email;
	private String mobileNumber;

	@ManyToOne
	@JoinColumn(name = "event_id")
	@JsonIgnore
	private Event event;
}
