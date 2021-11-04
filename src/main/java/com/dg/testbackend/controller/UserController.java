package com.dg.testbackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dg.testbackend.exception.UserNotFoundException;
import com.dg.testbackend.model.UserEntity;
import com.dg.testbackend.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping
	public ResponseEntity<List<UserEntity>> findAllUsers() {

		List<UserEntity> users = userService.findAll();

		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(users);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {

		Optional<UserEntity> user = userService.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException(id);
		}

		return ResponseEntity.ok(user.get());

	}

	@PostMapping
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {

		UserEntity createdUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(createdUser.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

		if (userService.delete(id)) {
			return ResponseEntity.noContent().build();
		} else {
			throw new UserNotFoundException(id);
		}

	}

	@PutMapping
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {

		Optional<UserEntity> updatedUser = userService.update(user);

		if (!updatedUser.isPresent()) {
			throw new UserNotFoundException(user.getId());
		}

		return ResponseEntity.ok(updatedUser.get());

	}

}
