package com.example.demo.repository.querydsl;

import com.example.demo.domain.User;
import com.example.demo.repository.JPABaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserQueryRepository extends JPABaseRepository<User, Long> {}
