package com.bittsoftware.springbootmongosample.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<PostDTO> findByTitle(String title) {
		List<Post> posts = repository.searchTitle(title);
		return posts.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
	}

	public List<PostDTO> fullSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());

		List<Post> posts = repository.fullSearch(text, startMoment, endMoment);
		return posts.stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
	}

	private Instant convertMoment(String moment, Instant alternative) {
		try {
			return Instant.parse(moment);
		} catch (DateTimeParseException e) {
			return alternative;
		}
	}
}
