package com.example.demo.repository;

import com.example.demo.domain.Political;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoliticalRepository extends JpaRepository<Political, Long> {

    Page<Political> findAllByDeletedFalse(Pageable pageable);

    Optional<Political>  findByDeletedFalseAndId(Long id);

}
