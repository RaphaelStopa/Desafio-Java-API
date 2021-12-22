package com.example.demo.repository.querydsl.impl;

import com.example.demo.domain.Political;
import com.example.demo.repository.PoliticalRepository;
import com.example.demo.service.PoliticalService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class PoliticalServiceImpl implements PoliticalService {

    private static final String ENTITY_NAME = "political";

    private final PoliticalRepository repository;

    public PoliticalServiceImpl(PoliticalRepository repository) {
        this.repository = repository;
    }


    @Override
    public Political save(Political political) {
        return repository.save(political);
    }

    @Override
    public Optional<Political> partialUpdate(Political political) {
        if (!repository.existsById(political.getId())) {
            throw BusinessException.badRequest();
        }
        return Optional.of(repository.save(political));
    }

    @Override
    public Page<Political> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Political> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
