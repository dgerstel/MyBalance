package com.mybalance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybalance.model.User;
import com.mybalance.model.UserRole;
import com.mybalance.repository.ActivityDiaryRepository;
import com.mybalance.repository.FoodDiaryRepository;
import com.mybalance.repository.UserRepository;
import com.mybalance.repository.UserRoleRepository;

@Service
public class UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	private UserRoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private FoodDiaryRepository foodDiaryRepo;
	private ActivityDiaryRepository actDiaryRepo;

	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(UserRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setFoodDiaryRepo(FoodDiaryRepository foodDiaryRepo) {
		this.foodDiaryRepo = foodDiaryRepo;
	}

	@Autowired
	public void setActDiaryRepo(ActivityDiaryRepository actDiaryRepo) {
		this.actDiaryRepo = actDiaryRepo;
	}

	public void addWithDefaultRole(User user) {
		UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
		user.setRole(defaultRole.getId());
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepository.save(user);
	}

	public User findUser(String name) {
		return userRepository.findByUserName(name);
	}

	public void updateUser(Integer id, User user) {
		Optional<User> userOpt = userRepository.findById(id);
		User userFromBase = userOpt.get();
		userFromBase.setAge(user.getAge());
		userFromBase.setWeight(user.getWeight());
		userFromBase.setJobActivityLevel(user.getJobActivityLevel());
		userRepository.save(userFromBase);
	}

	public Iterable<User> displayUser() {
		Iterable<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	public void deleteUser(Integer id) {
		foodDiaryRepo.deleteByUserId(id);
		actDiaryRepo.deleteByUserId(id);
		userRepository.deleteById(id);
	}
}
