package com.example.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.blog.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
