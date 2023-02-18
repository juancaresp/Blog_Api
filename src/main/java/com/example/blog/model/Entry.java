package com.example.blog.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Entry {

	@Id
	@EqualsAndHashCode.Include
	@Column(length = 25,nullable = false)
	private String title;
	
	@Column(length = 250)
	private String body;
	
	@Enumerated
	private Category category;
	
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
}
