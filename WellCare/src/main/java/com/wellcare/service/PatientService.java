package com.wellcare.service;

import java.util.List;

import com.wellcare.dto.PatientDto;

public interface PatientService {

	PatientDto createPatient(PatientDto patientDto);

	List<PatientDto> getAllPatients();

	PatientDto getPatientById(String id);

	PatientDto updatePatient(String id, PatientDto patientDto);

	void deletePatient(String id);

}
