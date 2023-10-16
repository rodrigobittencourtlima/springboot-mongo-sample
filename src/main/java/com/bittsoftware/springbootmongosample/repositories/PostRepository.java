package com.bittsoftware.springbootmongosample.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bittsoftware.springbootmongosample.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String title);

	// https://www.mongodb.com/docs/manual/reference/operator/query/regex/#mongodb-query-op.-regex
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String title);

	@Query("{ $and: [ { 'moment': { $gte: ?1 } }, { 'moment': { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Instant startMoment, Instant endMoment);

}
