package com.bittsoftware.springbootmongosample.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittsoftware.springbootmongosample.models.dto.UserDTO;
import com.bittsoftware.springbootmongosample.models.entities.User;
import com.bittsoftware.springbootmongosample.repositories.UserRepository;
import com.bittsoftware.springbootmongosample.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDTO> getAllUsers() {
		return repository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
	}

	public UserDTO findById(String id) {
		Optional<User> user = repository.findById(id);
		User entity = user.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return new UserDTO(entity);
	}

	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.insert(entity);
		return new UserDTO(entity);
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
