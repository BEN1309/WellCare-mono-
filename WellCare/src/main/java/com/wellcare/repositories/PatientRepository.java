package com.wellcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellcare.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
