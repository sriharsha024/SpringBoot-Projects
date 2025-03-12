package com.project.event_management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "venue_name", nullable = false)
	private String name;
	private String location;

	@OneToMany(mappedBy = "venue")
	@JsonIgnore
	private List<Event> events;
}
