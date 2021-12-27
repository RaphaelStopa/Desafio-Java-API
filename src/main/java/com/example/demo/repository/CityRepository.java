package com.example.demo.repository;

import com.example.demo.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAllByDeletedFalse(Pageable pageable);

    Optional<City> findByDeletedFalseAndId(Long id);
}
