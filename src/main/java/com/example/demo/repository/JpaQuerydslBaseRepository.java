package com.example.demo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.SQLTemplatesRegistry;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;
import static java.util.Optional.of;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

@SuppressWarnings({ "unchecked", "NullableProblems", "unused", "SpringDataMethodInconsistencyInspection", "ConstantConditions", "WeakerAccess" })
@NoRepositoryBean
public abstract class JpaQuerydslBaseRepository<ID extends Serializable, T>
    implements Serializable, JPABaseRepository<T, ID> {

    protected static final String SEARCH_PARAM = "search";
    private static final long serialVersionUID = -7951474930842466983L;
    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    protected SimpleJpaRepository<T, ID> jpaRepository;

    @PersistenceContext
    protected EntityManager manager;

    protected EntityPath<T> path;

    protected PathBuilder<T> builder;

    protected Querydsl querydsl;

    protected JPAQueryFactory queryFactory;

    protected Supplier<JPASQLQuery<T>> jpaSqlFactory;

    @PostConstruct
    public void init() throws SQLException {
        configure(this.manager);
    }

    protected Optional<Predicate> enumBind(EnumPath path, Collection<? extends Enum<?>> values) {
        BooleanBuilder expression = new BooleanBuilder();
        values.forEach(it -> expression.or(path.eq(it)));
        return of(expression);
    }

    protected void configure(EntityManager em) throws SQLException {
        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) (type).getActualTypeArguments()[1];
        this.manager = em;
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityClass);
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(em, builder);
        this.jpaRepository = new SimpleJpaRepository<>(entityClass, em);
        SQLTemplates sqlTemplates = getSQLServerTemplates(em.getEntityManagerFactory());
        this.jpaSqlFactory = () -> new JPASQLQuery<>(em, sqlTemplates);
        queryFactory = new JPAQueryFactory(HQLTemplates.DEFAULT, em);
    }

    protected EntityManager getEntityManager() {
        return this.manager;
    }

    @Override
    public Optional<T> findOne(Predicate predicate) {
        T o = createQuery(predicate).fetchOne();
        return Optional.ofNullable(o);
    }

    @Override
    public List<T> findAll(Predicate predicate) {
        return createQuery(predicate).fetch();
    }

    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return (List<T>) querydsl.createQuery(path).where(predicate).orderBy(orders).fetch();
    }

    public List<T> findAllEager(Predicate predicate, OrderSpecifier<?>... orders) {
        return (List<T>) querydsl.createQuery(path).where(predicate).orderBy(orders).fetchAll().fetch();
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        JPQLQuery countQuery = createQuery(predicate);
        long total = countQuery.fetchCount();

        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));
        List<T> content = total > pageable.getOffset() ? query.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<T> findAll(JPQLQuery query, Pageable pageable) {
        JPQLQuery pagedQuery = querydsl.applyPagination(pageable, query);
        long total = query.fetchCount();

        List<T> content = total > pageable.getOffset() ? pagedQuery.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<T> findAll(JPAQuery<T> query, Pageable pageable) {
        JPQLQuery pagedQuery = querydsl.applyPagination(pageable, query);
        long total = query.fetchCount();

        List<T> content = total > pageable.getOffset() ? pagedQuery.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public long count(Predicate predicate) {
        return createQuery(predicate).fetchCount();
    }

    public JPQLQuery<T> createQuery(Predicate... predicate) {
        return (JPQLQuery<T>) querydsl.createQuery(path).where(predicate);
    }

    @Override
    public List<T> findAll() {
        return this.jpaRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.jpaRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.jpaRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return this.jpaRepository.count();
    }

    @Override
    public void delete(T entity) {
        this.jpaRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        this.jpaRepository.deleteAll();
    }

    @Override
    public <S extends T> S save(S entity) {
        return this.jpaRepository.save(entity);
    }

    @Override
    public T getOne(ID id) {
        return this.jpaRepository.getOne(id);
    }

    @Override
    public boolean existsById(ID id) {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return this.jpaRepository.saveAndFlush(entity);
    }

    @Override
    public void flush() {
        this.jpaRepository.flush();
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        this.jpaRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        this.jpaRepository.deleteAllInBatch();
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return this.jpaRepository.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return this.jpaRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return this.jpaRepository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return this.jpaRepository.findAll(spec, sort);
    }

    @Override
    public long count(Specification<T> spec) {
        return this.jpaRepository.count(spec);
    }

    @Override
    public Iterable<T> findAll(Predicate predicate, Sort sort) {
        return (Iterable<T>) querydsl.createQuery(path).where(predicate);
    }

    @Override
    public boolean exists(Predicate predicate) {
        return querydsl.createQuery(path).where(predicate).fetchCount() > 0;
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return jpaRepository.findOne(example);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return jpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return jpaRepository.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return jpaRepository.exists(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return jpaRepository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return jpaRepository.findAll(example, sort);
    }

    @Override
    public Optional<T> findById(@NotNull ID id) {
        return jpaRepository.findById(id);
    }

    public T query(Function<JPAQuery<T>, T> query) {
        return query.apply((JPAQuery<T>) queryFactory.query());
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return jpaRepository.findAllById(ids);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return jpaRepository.saveAll(entities);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        jpaRepository.deleteAll(entities);
    }

    @Override
    public Iterable<T> findAll(OrderSpecifier<?>[] orders) {
        return querydsl.createQuery(path).orderBy(orders).createQuery().getResultList();
    }

    @Transactional
    public long update(Function<JPAUpdateClause, Long> update) {
        return update.apply(queryFactory.update(path));
    }

    @Transactional
    public void update(Consumer<JPAUpdateClause> update) {
        update.accept(queryFactory.update(path));
    }

    @Transactional
    public long deleteWhere(Predicate predicate) {
        return queryFactory.delete(path).where(predicate).execute();
    }

    public <O> O jpaSqlQuery(Function<JPASQLQuery<T>, O> query) {
        return query.apply(jpaSqlFactory.get());
    }

    public SubQueryExpression<T> jpaSqlSubQuery(Function<JPASQLQuery<T>, SubQueryExpression<T>> query) {
        return jpaSqlQuery(query);
    }

    protected PageableQueryBuilder<T> createPaginationQuery() {
        return new PageableQueryBuilder<>(querydsl, manager);
    }

    protected Page<T> pagingQuery(
        @Nonnull BiConsumer<JPAQuery<T>, BooleanBuilder> queryFunction,
        Pageable pageable,
        @Nullable Path<?>... sortParameters
    ) {
        Sort sort = pageable.getSort();

        if (sort == null && nonNull(sortParameters) && isNotEmpty(sortParameters)) {
            sort = Sort.by(Sort.Direction.ASC, Arrays.stream(sortParameters).map(it -> it.getMetadata().getName()).toArray(String[]::new));
        }

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        JPAQuery<T> query = new JPAQuery<>(manager);
        BooleanBuilder where = new BooleanBuilder();

        queryFunction.accept(query, where);

        //        query.where(where);

        long total = query.fetchCount();

        JPQLQuery jpqlQuery = querydsl.applyPagination(pageRequest, query);
        List<T> content = total > pageRequest.getOffset() ? jpqlQuery.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageRequest, total);
    }

    protected Page<T> pagingQuerySorted(Pageable pageable, @Nullable Path<?>... sortParameters) {
        Sort sort = pageable.getSort();

        if (sort == null && nonNull(sortParameters) && isNotEmpty(sortParameters)) {
            sort = Sort.by(Sort.Direction.ASC, Arrays.stream(sortParameters).map(it -> it.getMetadata().getName()).toArray(String[]::new));
        }

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        JPAQuery<T> query = new JPAQuery<>(manager);
        BooleanBuilder where = new BooleanBuilder();

        query.where(where);

        long total = query.fetchCount();

        JPQLQuery jpqlQuery = querydsl.applyPagination(pageRequest, query);
        List<T> content = total > pageRequest.getOffset() ? jpqlQuery.fetch() : Collections.emptyList();

        return new PageImpl<>(content, pageRequest, total);
    }

    protected void commit() {
        getEntityManager().getTransaction().commit();
    }

    protected void begin() {
        getEntityManager().getTransaction().begin();
    }

    private SQLTemplates getSQLServerTemplates(EntityManagerFactory entityManagerFactory) throws SQLException {
        DatabaseMetaData databaseMetaData = getDatabaseMetaData(entityManagerFactory.createEntityManager());
        return new SQLTemplatesRegistry().getTemplates(databaseMetaData);
    }

    private DatabaseMetaData getDatabaseMetaData(EntityManager entityManager) throws SQLException {
        SessionImplementor sessionImplementor = entityManager.unwrap(SessionImplementor.class);
        DatabaseMetaData metaData = sessionImplementor.connection().getMetaData();
        entityManager.close();
        return metaData;
    }

    protected JPAQuery<T> getQuery() {
        return (JPAQuery<T>) querydsl.createQuery(path).from(path);
    }
}
