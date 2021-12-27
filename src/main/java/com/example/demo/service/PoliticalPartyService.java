package com.example.demo.service;

import com.example.demo.domain.PoliticalParty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PoliticalPartyService {

    PoliticalParty save(PoliticalParty politicalParty);

    Page<PoliticalParty> findAll(Pageable pageable);

    Optional<PoliticalParty> findOne(Long id);

    void delete(Long id);
}
