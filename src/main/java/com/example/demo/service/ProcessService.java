package com.example.demo.service;

import com.example.demo.domain.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProcessService {
    Process save(Process process);

    Page<Process> findAll(Pageable pageable);

    Optional<Process> findOne(Long id);

    void delete(Long id);
}
