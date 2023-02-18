package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.blog.model.Entry;
import com.example.blog.model.User;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.EntryRepository;
import com.example.blog.repository.UserRepository;

@Service
public class EntryServiceImp implements EntryService {

	@Autowired UserRepository userR;
	@Autowired EntryRepository entryR;
	@Autowired CommentRepository commentR;
	
	@Override
	public boolean insert(Entry e) {
		boolean exito=false;
		
		if(entryR.findById(e.getTitle()).isEmpty()) {
			entryR.save(e);
			exito=true;
		}
		return exito;
	}

	@Override
	public boolean update(Entry e) {
		boolean exito=false;
		
		if(entryR.findById(e.getTitle()).isPresent()) {
			entryR.save(e);
			exito=true;
		}
		return exito;
	}

	@Override
	public boolean delete(String title) {
		boolean exito=false;
		
		if(entryR.findById(title).isPresent()) {
			entryR.deleteById(title);
			exito=true;
		}
		return exito;
	}

	@Override
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		return (List<Entry>) entryR.findAll();
	}

	@Override
	public Optional<Entry> findByTitle(String title) {
		// TODO Auto-generated method stub
		return entryR.findById(title);
	}

	@Override
	public List<Entry> findByUser(String nick) {
		// TODO Auto-generated method stub
		
		return entryR.findByUser(userR.findById(nick).orElse(new User()));
	}

}
