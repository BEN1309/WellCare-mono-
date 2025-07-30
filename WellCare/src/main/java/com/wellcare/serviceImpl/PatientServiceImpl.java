package com.wellcare.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wellcare.dto.PatientDto;
import com.wellcare.entities.Patient;
import com.wellcare.exceptions.ResourceNotFoundException;
import com.wellcare.repositories.PatientRepository;
import com.wellcare.service.PatientService;
import com.wellcare.util.PatientIdGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final PatientIdGenerator patientIdGenerator;

//Entity → DTO
	private PatientDto mapToDto(Patient patient) {
		return PatientDto.builder()
				.id(patient.getId())
				.name(patient.getName())
				.phone(patient.getPhone())
				.age(patient.getAge())
				.address(patient.getAddress())
				.build();
	}

// DTO → Entity
	private Patient mapToEntity(PatientDto dto) {
		return Patient.builder()
				.id(dto.getId())
				.name(dto.getName())
				.phone(dto.getPhone())
				.age(dto.getAge())
				.address(dto.getAddress())
				.build();
	}

	@Override
	@Transactional
	@CacheEvict(value = "patients", allEntries = true)
	public PatientDto createPatient(PatientDto patientDto) {
		Patient patient = mapToEntity(patientDto);
		
		String generatedId = patientIdGenerator.generatePatientId(patient.getName());
		patient.setId(generatedId);
		
		Patient saved = patientRepository.save(patient);
		return mapToDto(saved);
	}

	@Override
	@Cacheable("patients")
	public List<PatientDto> getAllPatients() {
		return patientRepository.findAll()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
	}

	@Override
	public PatientDto getPatientById(String id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: " + id));
		return mapToDto(patient);
	}

	@Override
	public PatientDto updatePatient(String id, PatientDto patientDto) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Patient not Found with id : "+id));

		patient.setName(patientDto.getName());
		patient.setPhone(patientDto.getPhone());
		patient.setAge(patientDto.getAge());
		patient.setAddress(patientDto.getAddress());

		Patient updated = patientRepository.save(patient);
		return mapToDto(updated);
	}

	@Override
	public void deletePatient(String id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
		patientRepository.delete(patient);
	}

}
