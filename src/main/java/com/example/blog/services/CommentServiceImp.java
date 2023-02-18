package com.example.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Comment;
import com.example.blog.model.Entry;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.EntryRepository;
import com.example.blog.repository.UserRepository;

@Service
public class CommentServiceImp implements CommentService {
	@Autowired UserRepository userR;
	@Autowired EntryRepository entryR;
	@Autowired CommentRepository commentR;
	@Override
	public boolean insert(Comment c) {
		
		commentR.save(c);
		return true;
	}

	@Override
	public boolean update(Comment c) {
		boolean exito=false;
		if(commentR.findById(c.getId()).isPresent()) {
			commentR.save(c);
			exito=true;
		}
		return exito;
	}

	@Override
	public boolean delete(Integer id) {
		boolean exito=false;
		if(commentR.findById(id).isPresent()) {
			commentR.deleteById(id);
			exito=true;
		}
		return exito;
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return (List<Comment>) commentR.findAll();
	}



	@Override
	public List<Comment> findByUser(String nick) {
		// TODO Auto-generated method stub
		return commentR.findByAuthor(nick);
	}

	@Override
	public List<Comment> findByEntry(String title) {
		// TODO Auto-generated method stub
		Optional<Entry>e=entryR.findById(title);
		if(e.isEmpty()) {
			return new ArrayList<>();
		}
		return commentR.findByEntry(e.get());
	}

	@Override
	public Optional<Comment> findById(Integer id) {
		// TODO Auto-generated method stub
		return commentR.findById(id);
	}

}
