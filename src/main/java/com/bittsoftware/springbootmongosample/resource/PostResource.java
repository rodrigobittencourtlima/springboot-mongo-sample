package com.bittsoftware.springbootmongosample.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bittsoftware.springbootmongosample.models.dto.PostDTO;
import com.bittsoftware.springbootmongosample.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "start", defaultValue = "") String start,
			@RequestParam(value = "end", defaultValue = "") String end) {
		return ResponseEntity.ok().body(service.fullSearch(text, start, end));
	}

}
