package com.example.blog.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Entity(name="Custom_User")
public class User {
	@Id
	@EqualsAndHashCode.Include
	@Column(nullable = false,length = 20)
	private String nickname;
	
	@Column(length = 50)
	private String password;
	
	private int points;
}
