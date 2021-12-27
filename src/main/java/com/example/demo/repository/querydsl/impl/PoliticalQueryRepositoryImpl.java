package com.example.demo.repository.querydsl.impl;

import com.example.demo.domain.Political;
import com.example.demo.domain.QPolitical;

import com.example.demo.repository.JpaQuerydslBaseRepository;
import com.example.demo.repository.querydsl.PoliticalQueryRepository;
import com.querydsl.core.types.Predicate;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PoliticalQueryRepositoryImpl extends JpaQuerydslBaseRepository<Long, Political> implements PoliticalQueryRepository, QuerydslBinderCustomizer<QPolitical> {


    private static final QPolitical $ = QPolitical.political;



    @Override
    public void customize(QuerydslBindings bindings, QPolitical root) {
        bindings.bind(root.lawProjects).all(ExpressionProviderFactory::getPredicate);

    }

    @Override
    public <S extends Political> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Political> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public Political getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    public Page<Political> findAllForUser(Pageable pageable, Predicate predicate, Long numberOfLaws) {

        return pagingQuery(
                (query, where) -> {
                    query.select($).from($).where(predicate);
                    if(Optional.ofNullable(numberOfLaws).isPresent()) {
                        query.where($.lawProjects.size().eq(Math.toIntExact(numberOfLaws)));
                    }
                },
                pageable
        );
    }
}
