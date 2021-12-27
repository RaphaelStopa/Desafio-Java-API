package com.example.demo.service;

import com.example.demo.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {
    City save(City city);

    Page<City> findAll(Pageable pageable);

    Optional<City> findOne(Long id);

    void delete(Long id);
}
