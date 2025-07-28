package com.wellcare.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wellcare.dto.DoctorDto;
import com.wellcare.entities.Doctor;
import com.wellcare.exceptions.ResourceNotFoundException;
import com.wellcare.repositories.DoctorRepository;
import com.wellcare.service.DoctorService;
import com.wellcare.util.DoctorIdGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;
	private final DoctorIdGenerator doctorIdGenerator;
	
	
	// Mapping: Entity → DTO
    private DoctorDto mapToDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .age(doctor.getAge())
                .specialization(doctor.getSpecialization())
                .available(doctor.isAvailable())
                .build();
    }

    // Mapping: DTO → Entity
    private Doctor mapToEntity(DoctorDto dto) {
        return Doctor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .age(dto.getAge())
                .specialization(dto.getSpecialization())
                .available(dto.isAvailable())
                .build();
    }
	
	
	@Override
	@Transactional
	public DoctorDto createDoctor(DoctorDto doctorDto) {
		 Doctor doctor = mapToEntity(doctorDto);
		 
		 String generatedId = doctorIdGenerator.generateDoctorId(doctor.getName());
		    doctor.setId(generatedId);
		 
	     Doctor saved = doctorRepository.save(doctor);
	     return mapToDto(saved);
	}

	@Override
	public List<DoctorDto> getAllDoctors() {
		return doctorRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
	}

	@Override
	public DoctorDto getDoctorById(String id) {
		Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return mapToDto(doctor);
	}

	@Override
	public DoctorDto updateDoctor(String id, DoctorDto doctorDto) {
		Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setAge(doctorDto.getAge());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setAvailable(doctorDto.isAvailable());

        Doctor updated = doctorRepository.save(doctor);
        return mapToDto(updated);
	}

	@Override
	public void deleteDoctor(String id) {
		 Doctor doctor = doctorRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
	        doctorRepository.delete(doctor);
	}



	
	
}
