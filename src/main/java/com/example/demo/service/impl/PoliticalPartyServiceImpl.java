package com.example.demo.service.impl;

import com.example.demo.domain.PoliticalParty;
import com.example.demo.repository.PoliticalPartyRepository;
import com.example.demo.service.PoliticalPartyService;
import com.example.demo.service.exceptions.BadRequestAlertException;
import com.example.demo.service.exceptions.BusinessException;
import liquibase.util.file.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

    private static final String ENTITY_NAME = "politicalParty";

    private final PoliticalPartyRepository repository;

    public PoliticalPartyServiceImpl(PoliticalPartyRepository repository) {
        this.repository = repository;
    }

    @Override
    public PoliticalParty save(PoliticalParty politicalParty) {
        if (politicalParty.getId() == null) {
            if(repository.existsByName(politicalParty.getName())) {
                throw new BadRequestAlertException("this name already exists", ENTITY_NAME, "already existing");
            }
        } else {
            var checkState =  repository.findById(politicalParty.getId()).orElseThrow();
            if (!checkState.getName().equals(politicalParty.getName())){
                if(repository.existsByName(politicalParty.getName())) {
                    throw new BadRequestAlertException("this name already exists", ENTITY_NAME, "already existing");
                }
            }
        }
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
