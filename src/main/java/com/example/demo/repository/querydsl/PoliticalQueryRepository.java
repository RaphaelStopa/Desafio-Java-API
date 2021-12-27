package com.example.demo.repository.querydsl;

import com.example.demo.domain.Political;
import com.example.demo.repository.JPABaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PoliticalQueryRepository extends JPABaseRepository<Political, Long> {}
