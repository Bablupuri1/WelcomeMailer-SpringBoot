package com.ecom.Service;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.EmailServiceForUser.EmailService;
import com.ecom.Model.Users;
import com.ecom.UserRespository.UserRepository;
import com.ecom.generatepassword.GeneratePassword;
import com.ecom.payload.UserPayload;
import com.ecom.responseapi.ApiResponse;

@Service
public class UserService {

	// Inject UserRepository to perform DB operations
	@Autowired
	private UserRepository userRepository;

	// Used to map UserPayload to Users entity
	@Autowired
	private ModelMapper modelMapper;

	// Used to generate random password
	@Autowired
	private GeneratePassword generatePassword;

	// Used to send email with credentials
	@Autowired
	EmailService emailservice;

	/**
	 * Create a new user only if mobile number is not already registered.
	 * If mobile is already registered, send email with existing credentials.
	 */
	public ApiResponse createUser(UserPayload userPayload) {
		try {
			// Validate if payload is null
			if (userPayload == null) {
				return new ApiResponse(false, "User object is null", HttpStatus.SC_BAD_REQUEST, userPayload);
			}

			// Validate name
			if (userPayload.getName() == null || userPayload.getName().trim().isEmpty()) {
				return new ApiResponse(false, "User name is null or empty", HttpStatus.SC_BAD_REQUEST, userPayload);
			}

			// Validate email
			if (userPayload.getEmail() == null || userPayload.getEmail().trim().isEmpty()) {
				return new ApiResponse(false, "User email is null or empty", HttpStatus.SC_BAD_REQUEST, userPayload);
			}

			// Validate mobile number
			if (userPayload.getMobile() == null || userPayload.getMobile().trim().isEmpty()) {
				return new ApiResponse(false, "User mobile number is null or empty", HttpStatus.SC_BAD_REQUEST, userPayload);
			}

			// Check if user already exists based on mobile number
			if (userRepository.existsByMobile(userPayload.getMobile())) {

				// Get existing user
				Users existingUser = userRepository.findByMobile(userPayload.getMobile());

				// Send existing credentials via email
				emailservice.sendUserCredentials(existingUser.getEmail(), existingUser.getName(), existingUser.getPassword());

				// Return response - duplicate mobile
				return new ApiResponse(false, "User already exists. Credentials resent to email.", HttpStatus.SC_CONFLICT, existingUser);
			} else {

				// Convert payload to Users entity
				Users user = modelMapper.map(userPayload, Users.class);

				// Generate and set random password
				user.setPassword(generatePassword.generatePassword());

				// Save user to DB
				Users savedUser = userRepository.save(user);

				// Send credentials via email
				emailservice.sendUserCredentials(savedUser.getEmail(), savedUser.getName(), savedUser.getPassword());

				// Return success response
				return new ApiResponse(true, "User registered successfully", HttpStatus.SC_OK, savedUser);
			}

		} catch (Exception e) {
			e.printStackTrace();

			// Return error response in case of exception
			return new ApiResponse(false, "Server error", HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * Fetch all users from DB
	 */
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Fetch single user by ID
	 */
	public Optional<Users> getUserById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Update user data for given ID
	 */
	public Users updateUser(Long id, Users updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			return userRepository.save(user);
		}).orElse(null);
	}

	/**
	 * Delete user by ID if exists
	 */
	public boolean deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
