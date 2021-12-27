package com.example.demo.repository;


import com.example.demo.domain.PoliticalParty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
    Page<PoliticalParty> findAllByDeletedFalse(Pageable pageable);

    Optional<PoliticalParty> findByDeletedFalseAndId(Long id);

    boolean existsByName(String name);

}
