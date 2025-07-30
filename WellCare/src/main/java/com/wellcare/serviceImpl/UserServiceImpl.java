package com.wellcare.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.wellcare.dto.UserDto;
import com.wellcare.entities.Doctor;
import com.wellcare.entities.Patient;
import com.wellcare.entities.Role;
import com.wellcare.entities.Specialization;
import com.wellcare.entities.User;
import com.wellcare.repositories.DoctorRepository;
import com.wellcare.repositories.PatientRepository;
import com.wellcare.repositories.UserRepository;
import com.wellcare.service.UserService;
import com.wellcare.util.DoctorIdGenerator;
import com.wellcare.util.PatientIdGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final PasswordEncoder passwordEncoder;
	private final DoctorIdGenerator doctorIdGenerator;
	private final PatientIdGenerator patientIdGenerator;

	@Override
	public User saveUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(userDto.getRole()); // Ensure Role is not null
		User savedUser = userRepository.save(user);

		// 👉 If role is DOCTOR, create Doctor entry
		if (userDto.getRole() == Role.DOCTOR) {
			Doctor doctor = new Doctor();

			doctor.setId(doctorIdGenerator.generateDoctorId(savedUser.getUsername())); // custom Doc id

			doctor.setName(savedUser.getUsername());
			doctor.setSpecialization(Specialization.GENERAL_PHYSICIAN);
			doctor.setAvailable(true);
			doctor.setUser(savedUser); // Ensure Doctor has user reference
			doctorRepository.save(doctor);
		}

		// 👉 If role is DOCTOR, create Doctor entry
		if (userDto.getRole() == Role.PATIENT) {
			Patient patient = new Patient();
			patient.setId(patientIdGenerator.generatePatientId(savedUser.getUsername()));
			patient.setName(savedUser.getUsername());
			patient.setAge(0); // default
			patient.setPhone(null); // default
			patient.setAddress(null); // or create a dummy Address
			patient.setUser(savedUser);
			patientRepository.save(patient);
		}

		return savedUser;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

}
