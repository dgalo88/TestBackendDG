package com.dg.testbackend.service;

import java.util.List;
import java.util.Optional;

import com.dg.testbackend.model.UserEntity;

public interface IUserService {

	public List<UserEntity> findAll();

	public Optional<UserEntity> findById(Long id);

	public UserEntity save(UserEntity user);

	public boolean delete(Long id);

	public Optional<UserEntity> update(UserEntity user);

}
