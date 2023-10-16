package com.bittsoftware.springbootmongosample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittsoftware.springbootmongosample.models.dto.PostDTO;
import com.bittsoftware.springbootmongosample.models.entities.Post;
import com.bittsoftware.springbootmongosample.repositories.PostRepository;
import com.bittsoftware.springbootmongosample.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public PostDTO findById(String id) {
		Post entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return new PostDTO(entity);
	}
}
