package com.example.demo.service.impl;

import com.example.demo.domain.State;
import com.example.demo.repository.StateRepository;
import com.example.demo.service.StateService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    private static final String ENTITY_NAME = "state";

    private final StateRepository repository;

    public StateServiceImpl(StateRepository repository) {
        this.repository = repository;
    }


    @Override
    public State save(State state) {
        return repository.save(state);
    }

    @Override
    public Page<State> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable);
    }

    @Override
    public Optional<State> findOne(@NotNull Long id) {
        return repository.findByDeletedFalseAndId(id);
    }

    @Override
    public void delete(Long id) {
        var state = repository.findById(id).orElseThrow();
        state.setDeleted(true);
        repository.save(state);
    }
}
