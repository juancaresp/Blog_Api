package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.EntryRepository;
import com.example.blog.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired UserRepository userR;
	@Autowired EntryRepository entryR;
	@Autowired CommentRepository commentR;
	
	@Override
	public boolean insert(User u) {
		// TODO Auto-generated method stub
		boolean exito=false;
		if(userR.findById(u.getNickname()).isEmpty()) {
			userR.save(u);
			exito=true;
		}
		return exito;
	}

	@Override
	public boolean update(User u) {
		boolean exito=false;
		if(userR.findById(u.getNickname()).isPresent()) {
			userR.save(u);
			exito=true;
		}
		return exito;
	}

	@Override
	public boolean delete(String id) {
		boolean exito=false;
		if(userR.findById(id).isPresent()) {
			userR.deleteById(id);
			exito=true;
		}
		return exito;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) userR.findAll();
	}

	@Override
	public Optional<User> findByNick(String nick) {
		// TODO Auto-generated method stub
		return userR.findById(nick);
	}

}
