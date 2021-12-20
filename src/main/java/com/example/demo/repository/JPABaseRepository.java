package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface JPABaseRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>, JpaSpecificationExecutor<T> {
    Optional<T> findById(@NotNull ID id);
}
