package com.example.blog.clients;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;


import org.springframework.web.client.RestTemplate;

import com.example.blog.model.Category;
import com.example.blog.model.Comment;
import com.example.blog.model.Entry;
import com.example.blog.model.User;

public class ClientRest {
	
	private static final String URIBASE="http://localhost:8080";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		load();
		createEntry();
		comment();
		findAll();
		showComments();
		deleteNoCommentsEntries();
		findMostComm();
		entriesOfMostPoints();
		entriesThisMonth();
	}

	
	private static void entriesThisMonth() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/entry/thismonth";
		
		
		List<String> comments=Stream.of(restTemplate.getForObject(uri, String[].class)).toList();
		comments.forEach(System.out::println);
	}


	private static void entriesOfMostPoints() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/user/morePointsEntList";
		
		
		List<Entry> comments=Stream.of(restTemplate.getForObject(uri, Entry[].class)).toList();
		comments.forEach(System.out::println);
	}


	private static void findMostComm() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/entry/mostComm";
		
		System.out.println(restTemplate.getForObject(uri, String.class));
		
	}


	private static void deleteNoCommentsEntries() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/entry/deleteNoComm";
		
		restTemplate.delete(uri);
		
		System.out.println("All deleted");
		
	}


	private static void showComments() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/comment/findByEntry/{title}";
		List<String> titles=Stream.of(restTemplate.getForObject(URIBASE+"/entry/findByCattTitle/{cat}", String[].class,"Sports")).toList();
		String title;
		do {
			titles.forEach(System.out::println);
			title="Potatoe";//we should ask for it
		}while(!titles.contains(title));
		
		List<Comment> comments=Stream.of(restTemplate.getForObject(uri, Comment[].class,title)).toList();
		comments.forEach(System.out::println);
		
	}

	
	
	private static void comment() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/comment/new";
		
		Comment c= Comment.builder()
						.author(User.builder()
								.nickname("Rosa")
								.password("Pass")
								.points(0)
								.build())
						.content("Nice")
						.entry(restTemplate.getForObject(URIBASE+"/entry/findBytitle/{title}", Entry.class,"Potatoe"))
						.value(0)
						.build();
		
		restTemplate.postForEntity(uri, c, Comment.class);
		
	}

	private static void findAll() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		List<Comment> comments=Stream.of(restTemplate.getForObject(URIBASE+"/comment/findall", Comment[].class)).toList();
		comments.forEach(System.out::println);
	}

	private static void load() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/app/load";
		System.out.println(restTemplate.getForObject(uri, String.class));
	}

	private static void createEntry() {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String uri=URIBASE+"/entry/new";
		//we ask for the entry and all that stuff
		Entry e=Entry.builder()
				.body("Text")
				.category(Category.ECONOMY)
				.date(LocalDate.now())
				.title("New Entry of sports")
				.user(User.builder()
						.nickname("Rosa")
						.password("Pass")
						.points(0)
						.build())
				.build();
					
		System.out.println(restTemplate.postForObject(uri, e, Entry.class));
	}

	
	
}
