package com.example.demo.service.impl;

import com.example.demo.domain.PoliticalParty;
import com.example.demo.repository.PoliticalPartyRepository;
import com.example.demo.service.PoliticalPartyService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

    private static final String ENTITY_NAME = "politicalParty";

    private final PoliticalPartyRepository repository;

    public PoliticalPartyServiceImpl(PoliticalPartyRepository repository) {
        this.repository = repository;
    }

    @Override
    public PoliticalParty save(PoliticalParty politicalParty) {
        return repository.save(politicalParty);
    }

    @Override
    public Page<PoliticalParty> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable);
    }

    @Override
    public Optional<PoliticalParty> findOne(Long id) {
        return repository.findByDeletedFalseAndId(id);
    }

    @Override
    public void delete(Long id) {
        var politicalParty = repository.findById(id).orElseThrow();
        politicalParty.setDeleted(true);
        repository.save(politicalParty);
    }
}