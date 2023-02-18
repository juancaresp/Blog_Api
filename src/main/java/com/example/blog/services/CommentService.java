package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import com.example.blog.model.Comment;


public interface CommentService {
	public boolean insert(Comment c);
	public boolean update(Comment c);
	public boolean delete(Integer id);
	public List<Comment> findAll();
	public Optional<Comment> findById(Integer id);
	public List<Comment> findByUser(String nick);
	public List<Comment> findByEntry(String title);
}
