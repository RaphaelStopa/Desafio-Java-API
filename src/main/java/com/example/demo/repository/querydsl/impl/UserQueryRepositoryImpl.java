package com.example.demo.repository.querydsl.impl;

import com.example.demo.domain.QUser;
import com.example.demo.domain.User;
import com.example.demo.repository.JpaQuerydslBaseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.querydsl.UserQueryRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

@Repository
public class UserQueryRepositoryImpl extends JpaQuerydslBaseRepository<Long, User> implements UserQueryRepository, QuerydslBinderCustomizer<QUser> {

    private static final QUser $ = QUser.user;

    private final EntityManager em;

    public UserQueryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(root.id).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(root.login).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(root.activated).first(BooleanExpression::eq);
    }
    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public User getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    public Page<User> findAllExemple(Pageable pageable, Predicate predicate) {
        return pagingQuery(
                (query, where) -> {
                    query.select($).from($).where(predicate);
                },
                pageable
        );
    }
}
