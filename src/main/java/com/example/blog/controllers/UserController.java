package com.example.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Entry;
import com.example.blog.model.User;
import com.example.blog.services.CommentService;
import com.example.blog.services.EntryService;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired CommentService commentS;
	@Autowired EntryService entryS;
	@Autowired UserService userS;
	
	@GetMapping("/morePointsEntList")
	public ResponseEntity<List<Entry>> morePointsEntList(){
		
		User user=userS.findAll().stream().sorted((u1,u2)-> u2.getPoints()-u1.getPoints()).findFirst().get();
		
		
		return new ResponseEntity<List<Entry>>(entryS.findByUser(user.getNickname()),HttpStatus.OK);
	}
}
