package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.blog.model.Comment;
import com.example.blog.model.Entry;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	//@Query("select c from ccomment c where c.author=?1")
	public List<Comment> findByAuthor(String nick);
	@Query("select c from ccomment c where c.entry=?1")
	public List<Comment> findByEntry(Entry entry);
}
