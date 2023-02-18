package com.example.blog.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Category;
import com.example.blog.model.Comment;
import com.example.blog.model.Entry;
import com.example.blog.model.User;
import com.example.blog.services.CommentService;
import com.example.blog.services.EntryService;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/app")
public class ControllerDefault {

	@Autowired CommentService commentS;
	@Autowired EntryService entryS;
	@Autowired UserService userS;
	
	@GetMapping("/load")
	public ResponseEntity<String> loadData(){
		ResponseEntity<String> response=new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		User u1,u2;
		Entry e1,e2;
		Comment c1,c2;
		u1=User.builder()
				.nickname("Evaristo")
				.password("Contra")
				.points(50)
				.build();
		u2=User.builder()
				.nickname("Lucia")
				.password("Contra")
				.points(20)
				.build();
		
		e1=Entry.builder()
				.body("Mucho texto")
				.category(Category.SPORTS)
				.date(LocalDate.now().minusWeeks(2))
				.title("Potatoe")
				.user(u1)
				.build();
		
		e2=Entry.builder()
				.body("Mucho texto")
				.category(Category.ECONOMY)
				.date(LocalDate.now().minusWeeks(40))
				.title("Entry2")
				.user(u2)
				.build();
		
		c1=Comment.builder()
				.author(u1)
				.content("I like it")
				.entry(e2)
				.value(2)
				.build();
		c2=Comment.builder()
				.author(u2)
				.content("I Love it")
				.entry(e1)
				.value(5)
				.build();
		
		if(userS.insert(u1)&&userS.insert(u2)
				&&entryS.insert(e1)&&entryS.insert(e2)
					&&commentS.insert(c1)&&commentS.insert(c2)) {
			response=new ResponseEntity<String>("All data loaded",HttpStatus.OK);
		}
		
		return response;
	}
	
}
