package org.springframework.data.orient.repository.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.SelectJoinStep;
import org.jooq.SelectLimitStep;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.repository.OrientRepository;
import org.springframework.data.orient.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

/**
 * Default implementation of the {@link org.springframework.data.repository.PagingAndSortingRepository} interface for OrientDB.
 * 
 * @author Dzmitry_Naskou
 * @param <T> the type of the entity to handle
 */
@Repository
@Transactional(readOnly = true)
public class SimpleOrientRepository<T> implements OrientRepository<T> {

    /** The orient operations. */
    protected final OrientOperations operations;
    
    /** The domain class. */
    protected final Class<T> domainClass;
    
    /** The repository interface. */
    protected final Class<?> repositoryInterface;

    /**
     * Instantiates a new {@link SimpleOrientRepository} from the given {@link OrientOperations} and domain class.
     *
     * @param operations the orinet operations
     * @param domainClass the domain class
     * @param repositoryInterface the target repository interface
     */
    public SimpleOrientRepository(OrientOperations operations, Class<T> domainClass, Class<?> repositoryInterface) {
        super();
        this.operations = operations;
        this.domainClass = domainClass;
        this.repositoryInterface = repositoryInterface;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#save(S)
     */
    @Transactional(readOnly = false)
    public <S extends T> S save(S entity) {
        return operations.save(entity);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#save(java.lang.Object, java.lang.String)
     */
    @Override
    @Transactional(readOnly = false)
    public <S extends T> S save(S entity, String cluster) {
        return operations.save(entity, cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#save(java.lang.Iterable)
     */
    @Transactional(readOnly = false)
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
    
        List<S> result = new ArrayList<S>();
    
        for (S entity : entities) {
            result.add(save(entity));
        }
    
        return result;
    }
    
    @Transactional(readOnly = false)
    public <S extends T> Iterable<S> save(Iterable<S> entities, String cluster) {
        if (entities == null) {
            return Collections.emptyList();
        }
    
        List<S> result = new ArrayList<S>();
    
        for (S entity : entities) {
            result.add(save(entity, cluster));
        }
    
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
     */
    public T findOne(String id) {
        return operations.load(new ORecordId(id));
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
     */
    public boolean exists(String id) {
        return findOne(id) != null;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#findAll()
     */
    @Override
    public List<T> findAll() {
        return operations.query(getQuery((Sort) null));
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#findAll(java.lang.Class)
     */
    @Override
    public <S extends T> List<S> findAll(Class<S> domainClass) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#findAll(java.lang.String)
     */
    @Override
    public List<T> findAll(String cluster) {
        return operations.query(getQuery(getSource(cluster), (Sort) null));
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#findAll(java.lang.Iterable)
     */
    @Override
    public List<T> findAll(Iterable<String> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#count()
     */
    @Override
    public long count() {
        return operations.countClass(domainClass);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#count(java.lang.String)
     */
    @Override
    public long count(String cluster) {
        return operations.countClusterElements(cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#count(java.lang.Class)
     */
    @Override
    public long count(Class<? extends T> domainClass) {
        return operations.countClass(domainClass);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
     */
    @Transactional(readOnly = false)
    public void delete(String id) {
        operations.delete(new ORecordId(id));
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        operations.delete(entity);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
     */
    @Transactional(readOnly = false)
    public void delete(Iterable<? extends T> entities) {
        for (T entity : entities) {
                delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#deleteAll()
     */
    @Transactional(readOnly = false)
    public void deleteAll() {
        for (T entity : operations.browseClass(domainClass)) {
            operations.delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#deleteAll(java.lang.String)
     */
    @Override
    public void deleteAll(String cluster) {
        for (T entity : findAll(cluster)) {
            operations.delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#deleteAll(java.lang.Class)
     */
    @Override
    public void deleteAll(Class<? extends T> domainClass) {
        for (T entity : findAll(domainClass)) {
            operations.delete(entity);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.OrientRepository#getDomainClass()
     */
    @Override
    public Class<T> getDomainClass() {
        return domainClass;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(Sort sort) {
        return operations.query(getQuery(sort));
    }

    public List<T> findAll(String cluster, Sort sort) {
        return operations.query(getQuery(getSource(cluster), sort));
    }
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<T>(findAll());
        }
        
        Long total = count();
        List<T> content = (List<T>) (total > pageable.getOffset() ? operations.query(getQuery(pageable)) : Collections.<T> emptyList());
        
        return new PageImpl<T>(content, pageable, total);
    }
    
    /**
     * Creates the query for the given {@link Sort}.
     *
     * @param sort the sort
     * @return the query
     */
    private OSQLQuery<T> getQuery(Sort sort) {
        return getQuery(getDefaultSource(), sort);
    }
    
    /**
     * Creates the query for the given source (class or cluster) and {@link Sort}.
     *
     * @param sort the sort
     * @return the query
     */
    private OSQLQuery<T> getQuery(String source, Sort sort) {
        DSLContext context = DSL.using(SQLDialect.MYSQL);
        SelectJoinStep<? extends Record> joinStep = context.select().from(source);
        
        Query query = sort == null ? joinStep : joinStep.orderBy(QueryUtils.toOrders(sort));
        
        return new OSQLSynchQuery<T>(query.getSQL(ParamType.INLINED));
    }
    
    /**
     * Creates the query for the given {@link Sort}.
     *
     * @param sort the sort
     * @return the query
     */
    private OSQLQuery<T> getQuery(Pageable pageable) {
        DSLContext context = DSL.using(SQLDialect.MYSQL);
        SelectJoinStep<? extends Record> joinStep = context.select().from(domainClass.getSimpleName());
        
        Sort sort = pageable.getSort();
        SelectLimitStep<? extends Record> limitStep = sort == null ? joinStep : joinStep.orderBy(QueryUtils.toOrders(sort));
        Query query = pageable == null ? limitStep : limitStep.limit(pageable.getPageSize()).offset(pageable.getOffset());
        
        return new OSQLSynchQuery<T>(query.getSQL(ParamType.INLINED));
    }
    
    private String getDefaultSource() {
        return domainClass.getSimpleName();
    }
    
    private String getSource(String cluster) {
        return "cluster:" + cluster;
    }
}
