package com.dg.testbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dg.testbackend.model.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
