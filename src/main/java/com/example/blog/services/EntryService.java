package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import com.example.blog.model.Entry;

public interface EntryService {
	public boolean insert(Entry e);
	public boolean update(Entry e);
	public boolean delete(String title);
	public List<Entry> findAll();
	public Optional<Entry> findByTitle(String title);
	public List<Entry> findByUser(String nick);
}
