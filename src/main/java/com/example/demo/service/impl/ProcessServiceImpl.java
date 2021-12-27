package com.example.demo.service.impl;

import com.example.demo.domain.Process;
import com.example.demo.domain.State;
import com.example.demo.repository.ProcessRepository;
import com.example.demo.service.ProcessService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class ProcessServiceImpl implements ProcessService {

    private static final String ENTITY_NAME = "process";

    private final ProcessRepository repository;

    public ProcessServiceImpl(ProcessRepository repository) {
        this.repository = repository;
    }


    @Override
    public Process save(Process process) {
        return repository.save(process);
    }


    @Override
    public Page<Process> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Process> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
