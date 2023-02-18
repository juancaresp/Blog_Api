package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import com.example.blog.model.User;

public interface UserService {

	public boolean insert(User u);
	public boolean update(User u);
	public boolean delete(String id);
	public List<User> findAll();
	public Optional<User> findByNick(String nick);
}
