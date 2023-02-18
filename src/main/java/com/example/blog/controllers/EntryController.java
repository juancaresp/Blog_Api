package com.example.blog.controllers;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.blog.model.Entry;
import com.example.blog.model.Category;
import com.example.blog.services.CommentService;
import com.example.blog.services.EntryService;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/entry")
public class EntryController {
	
	@Autowired CommentService commentS;
	@Autowired EntryService entryS;
	@Autowired UserService userS;
	
	@PostMapping("/new")
	public ResponseEntity<Entry> createEntry(@RequestBody Entry e){
		ResponseEntity<Entry> response=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		if(userS.findByNick(e.getUser().getNickname()).isEmpty()) {
			userS.insert(e.getUser());
		}
		if(entryS.insert(e)) {
			int sum=e.getCategory().ordinal()+1;
			
			e.getUser().setPoints(e.getUser().getPoints()+sum);
			userS.update(e.getUser());
			response=new ResponseEntity<Entry>(e,HttpStatus.OK);
		}
		
		return response;
	}
	
	
	@GetMapping("/findBytitle/{title}")
	public ResponseEntity<Entry> findBytitle(@PathVariable String title){
		return new ResponseEntity<Entry>(entryS.findByTitle(title).orElse(new Entry()),HttpStatus.OK);
	}
	
	
	@GetMapping("/findByCat/{cat}")
	public ResponseEntity<List<Entry>> findByCat(@PathVariable String cat){
		
		Category ca=Category.valueOf(cat.toUpperCase());
		
		List<Entry> entries=entryS.findAll().stream().filter(e->e.getCategory().equals(ca)).collect(Collectors.toList());
		
		return new ResponseEntity<List<Entry>>(entries,HttpStatus.OK);
	}
	
	@GetMapping("/findByCattTitle/{cat}")
	public ResponseEntity<List<String>> findByCatTitle(@PathVariable String cat){
		
		Category ca=Category.valueOf(cat.toUpperCase());
		
		List<String> entries=entryS.findAll().stream().filter(e->e.getCategory().equals(ca)).map(c->c.getTitle()).collect(Collectors.toList());
		
		return new ResponseEntity<List<String>>(entries,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNoComm")
	public ResponseEntity<String> deleteNoComm(){
		
		List<String> ewhit=commentS.findAll().stream().map(c->c.getEntry().getTitle()).distinct().collect(Collectors.toList());
		
		List<String> entries=entryS.findAll().stream().map(c->c.getTitle()).collect(Collectors.toList());
		
		for(int i = 0 ; i< entries.size();i++) {
			String entry=entries.get(i);
			if(!ewhit.contains(entry)) {
				entryS.delete(entry);
			}
		}
		
		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	}
	
	@GetMapping("/mostComm")
	public ResponseEntity<String> findMostComm(){
		
		
		List<String> entries=entryS.findAll().stream().map(c->c.getTitle()).collect(Collectors.toList());
		String e="";
		int max=0;
		int n;
		for(int i=0;i<entries.size();i++) {
			n=commentS.findByEntry(entries.get(i)).size();
			if(n>max) {
				e=entries.get(i);
				max=n;
			}
		}
		
		return new ResponseEntity<String>(e,HttpStatus.OK);
	}
	@GetMapping("/thismonth")
	public ResponseEntity<List<String>> findthismonth(){
		
		
		List<String> entries=entryS.findAll().stream().filter(e->e.getDate().isAfter(LocalDate.now().minusMonths(1))).map(c->c.getTitle()+"\n"+c.getBody()).collect(Collectors.toList());
		
		
		return new ResponseEntity<List<String>>(entries,HttpStatus.OK);
	}
}
