package com.example.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Comment;

import com.example.blog.services.CommentService;
import com.example.blog.services.EntryService;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired CommentService commentS;
	@Autowired EntryService entryS;
	@Autowired UserService userS;
	
	@PostMapping("/new")
	public ResponseEntity<Comment> createComment(@RequestBody Comment c){
		ResponseEntity<Comment> response=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
		if(userS.findByNick(c.getAuthor().getNickname()).isPresent()&&
				entryS.findByTitle(c.getEntry().getTitle()).isPresent()&&
					commentS.insert(c)) {
			response=new ResponseEntity<Comment>(c,HttpStatus.OK);
		}
		
		return response;
	}
	@GetMapping("/findall")
	public ResponseEntity<List<Comment>> createEntry(){

		return new ResponseEntity<List<Comment>>(commentS.findAll(),HttpStatus.OK);
	}
	@GetMapping("/findByEntry/{title}")
	public ResponseEntity<List<Comment>> findByEntry(@PathVariable String title){

		List<Comment> comments=commentS.findByEntry(title);

		return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
	}
}
