package com.example.demo.repository;

import com.example.demo.domain.City;
import com.example.demo.domain.LawProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
