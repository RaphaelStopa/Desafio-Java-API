package com.example.demo.service.impl;

import com.example.demo.domain.State;
import com.example.demo.repository.StateRepository;
import com.example.demo.service.StateService;
import com.example.demo.service.exceptions.BadRequestAlertException;
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

        if (state.getId() == null) {
            if(repository.existsByAcronym(state.getAcronym())) {
                throw new BadRequestAlertException("this acronym already exists", ENTITY_NAME, "already existing");
            }

            if(repository.existsByName(state.getName())) {
                throw new BadRequestAlertException("this name already exists", ENTITY_NAME, "already existing");
            }
        } else {
           var checkState =  repository.findById(state.getId()).orElseThrow();
            if (!checkState.getName().equals(state.getName())){
                if(repository.existsByName(state.getName())) {
                    throw new BadRequestAlertException("this name already exists", ENTITY_NAME, "already existing");
                }
            }
            if (!checkState.getAcronym().equals(state.getAcronym())){
                if(repository.existsByName(state.getAcronym())) {
                    throw new BadRequestAlertException("this acronym already exists", ENTITY_NAME, "already existing");
                }
            }
        }

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
