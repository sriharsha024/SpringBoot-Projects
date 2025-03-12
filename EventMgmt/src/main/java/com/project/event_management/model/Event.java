package com.project.event_management.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Title can't be null")
	@Size(min = 1, max = 255, message = "Title should be between 1 and 255 characters")
	private String title;

	@NotNull(message = "Date and Time can't be null")
	private LocalDateTime dateTime;

	@OneToMany(mappedBy = "event")
	private List<Attendee> attendees;

	@ManyToOne
	@JoinColumn(name = "venue_id")
	@NotNull(message = "Venue is required")
	private Venue venue;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull(message = "Category is required")
	private Category category;
}
