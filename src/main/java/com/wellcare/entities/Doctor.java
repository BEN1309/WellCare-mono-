package com.wellcare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {

	@Id
	private String id;

	@Column(name = "doc_name", nullable = false)
	private String name;

	@Column(name = "doc_mobile")
	private String phone;

	@Column(name = "doc_age")
	private int age;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Specialization specialization;
	
	@Builder.Default
	private boolean available = true;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
