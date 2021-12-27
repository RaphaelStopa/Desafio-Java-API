package com.example.demo.service;

import com.example.demo.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StateService {

    State save(State state);

    Page<State> findAll(Pageable pageable);

    Optional<State> findOne(Long id);

    void delete(Long id);
}
