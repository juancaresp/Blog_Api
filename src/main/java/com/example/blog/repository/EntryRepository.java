package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.blog.model.Entry;
import com.example.blog.model.User;

public interface EntryRepository extends CrudRepository<Entry, String> {
	
	@Query("select e from Entry e where e.user=?1")
	public List<Entry> findByUser(User user);
}
