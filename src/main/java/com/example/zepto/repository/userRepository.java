package com.example.zepto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zepto.model.user;

public interface userRepository extends JpaRepository<user,Long>{
	Optional<user>findUserByEmail(String email);

}
