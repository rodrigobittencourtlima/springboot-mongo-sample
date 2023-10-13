package com.bittsoftware.springbootmongosample.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittsoftware.springbootmongosample.models.dto.UserDTO;
import com.bittsoftware.springbootmongosample.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDTO> getAllUsers() {
		return repository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
	}

}
