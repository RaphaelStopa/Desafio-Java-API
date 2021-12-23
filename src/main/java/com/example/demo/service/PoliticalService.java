package com.example.demo.service;

import com.example.demo.domain.Political;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PoliticalService {

    Page<Political> findAllForUser(Pageable pageable, Predicate predicate, Long numberOfLaws);

    Political save(Political political);

    Page<Political> findAll(Pageable pageable);

    Optional<Political> findOne(Long id);

    void delete(Long id);
}
