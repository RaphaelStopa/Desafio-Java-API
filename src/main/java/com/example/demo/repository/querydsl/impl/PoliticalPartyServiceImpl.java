package com.example.demo.repository.querydsl.impl;

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
    public Optional<PoliticalParty> partialUpdate(PoliticalParty politicalParty) {
        if (!repository.existsById(politicalParty.getId())) {
            throw BusinessException.badRequest();
        }
        return Optional.of(repository.save(politicalParty));
    }

    @Override
    public Page<PoliticalParty> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<PoliticalParty> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
