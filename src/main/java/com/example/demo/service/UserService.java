package com.example.demo.service;


import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

//    Planet save(Planet planet);
//
//    Optional<Planet> partialUpdate(Planet planet);
//
    Page<User> findAll(Pageable pageable);

    String findEmailByLogin(String email);
//
//    Optional<Planet> findOne(Long id);
//
//    void checkByName(String name);
//
//    void delete(Long id);
}