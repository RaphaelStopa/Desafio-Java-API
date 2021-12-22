package com.example.demo.repository;

import com.example.demo.domain.Political;
import com.example.demo.domain.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticalRepository extends JpaRepository<Political, Long> {

}
