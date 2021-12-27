package com.example.demo.repository.querydsl.impl;

import com.example.demo.domain.QUser;
import com.example.demo.domain.User;
import com.example.demo.repository.JpaQuerydslBaseRepository;
import com.example.demo.repository.querydsl.UserQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserQueryRepositoryImpl extends JpaQuerydslBaseRepository<Long, User> implements UserQueryRepository, QuerydslBinderCustomizer<QUser> {


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
}
