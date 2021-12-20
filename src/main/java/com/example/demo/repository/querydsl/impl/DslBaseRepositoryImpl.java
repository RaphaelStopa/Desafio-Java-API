package com.example.demo.repository.querydsl.impl;


import com.example.demo.domain.QAuthority;
import com.example.demo.domain.QUser;
import com.example.demo.repository.querydsl.DslBaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public abstract class DslBaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements DslBaseRepository<T, ID> {

    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    protected  final QUser user = QUser.user;
    protected final QAuthority authority = QAuthority.authority;

    public DslBaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em=em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
}
