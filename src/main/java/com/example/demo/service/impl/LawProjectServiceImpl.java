package com.example.demo.service.impl;

import com.example.demo.domain.LawProject;
import com.example.demo.repository.LawProjectRepository;
import com.example.demo.service.LawProjectService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class LawProjectServiceImpl implements LawProjectService {

    private static final String ENTITY_NAME = "lawProject";

    private final LawProjectRepository repository;

    public LawProjectServiceImpl(LawProjectRepository repository) {
        this.repository = repository;
    }


    @Override
    public LawProject save(LawProject lawProject) {
        return repository.save(lawProject);
    }

    @Override
    public Page<LawProject> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<LawProject> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
