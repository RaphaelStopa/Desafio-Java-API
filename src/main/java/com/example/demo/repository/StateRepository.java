package com.example.demo.repository;

import com.example.demo.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Page<State> findAllByDeletedFalse(Pageable pageable);

    Optional<State> findByDeletedFalseAndId(Long id);

    boolean existsByName(String name);

    boolean existsByAcronym(String cronym);
}
