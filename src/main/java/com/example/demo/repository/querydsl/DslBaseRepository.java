package com.example.demo.repository.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DslBaseRepository<T, ID> extends JpaRepository<T, ID> { }
