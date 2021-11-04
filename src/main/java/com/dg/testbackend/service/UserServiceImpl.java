package com.dg.testbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.testbackend.model.UserEntity;
import com.dg.testbackend.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository repository;

	@Override
	public List<UserEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserEntity save(UserEntity user) {
		return user == null ? new UserEntity() : repository.save(user);
	}

	@Override
	public boolean delete(Long id) {

		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		}

		return false;

	}

	@Override
	public Optional<UserEntity> update(UserEntity user) {

		Long id = user.getId();

		if (repository.findById(id).isPresent()) {

			UserEntity updatedUser = new UserEntity();

			updatedUser.setId(id);
			updatedUser.setFirstname(user.getFirstname());
			updatedUser.setLastname(user.getLastname());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setUsername(user.getUsername());

			repository.save(updatedUser);

			return Optional.ofNullable(updatedUser);

		}

		return Optional.empty();

	}

}
