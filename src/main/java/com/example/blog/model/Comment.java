package com.example.blog.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Entity(name="ccomment")
public class Comment {
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Entry entry;
	
	@Column(length = 200)
	private String content;
	
	@Column(name = "valor")
	private int value;
}
