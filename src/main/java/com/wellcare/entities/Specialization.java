package com.wellcare.entities;

import jakarta.persistence.Table;

@Table(name = "specialization")
public enum Specialization {

		CARDIOLOGIST,
	    DERMATOLOGIST,
	    NEUROLOGIST,
	    PEDIATRICIAN,
	    ORTHOPEDIC,
	    PSYCHIATRIST,
	    GENERAL_PHYSICIAN
	
}
