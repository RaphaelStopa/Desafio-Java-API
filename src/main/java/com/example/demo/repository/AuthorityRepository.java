package com.example.demo.repository;

import com.example.demo.domain.Authority;
import com.example.demo.repository.querydsl.DslBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends DslBaseRepository<Authority, String> {
}