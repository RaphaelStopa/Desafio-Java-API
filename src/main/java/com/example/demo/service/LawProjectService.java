package com.example.demo.service;

import com.example.demo.domain.LawProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LawProjectService {
    LawProject save(LawProject lawProject);

    Page<LawProject> findAll(Pageable pageable);

    Optional<LawProject> findOne(Long id);

    void delete(Long id);
}
