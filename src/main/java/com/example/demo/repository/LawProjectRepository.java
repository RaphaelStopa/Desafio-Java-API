package com.example.demo.repository;

import com.example.demo.domain.LawProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawProjectRepository extends JpaRepository<LawProject, Long> {
}
