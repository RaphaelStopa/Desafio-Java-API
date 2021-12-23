package com.example.demo.service;

import com.example.demo.domain.Political;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PoliticalService {
    Political save(Political political);

    Page<Political> findAll(Pageable pageable);

    Optional<Political> findOne(Long id);

    void delete(Long id);
}
