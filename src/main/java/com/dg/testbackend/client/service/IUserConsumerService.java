package com.dg.testbackend.client.service;

import java.util.List;

import com.dg.testbackend.client.dto.UserDTO;

public interface IUserConsumerService {

	public List<UserDTO> findAll();

	public UserDTO create(UserDTO task);

	public UserDTO update(Long id, UserDTO task);

	public void delete(Long id);

}
