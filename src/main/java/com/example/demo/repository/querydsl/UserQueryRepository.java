package com.example.demo.repository.querydsl;

import com.example.demo.domain.User;
import com.example.demo.repository.JPABaseRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface UserQueryRepository extends JPABaseRepository<User, Long> {}
