package org.springframework.data.orient.repository.support;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.repository.annotation.Cluster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clustered implementation of the {@link org.springframework.data.repository.PagingAndSortingRepository} interface for OrientDB.
 * Uses custom cluster name for orient class instead default.
 * 
 * @author Dzmitry_Naskou
 * @param <T> the type of the entity to handle
 * @see Cluster
 */
@Repository
@Transactional(readOnly = true)
public class ClusteredOrientRepository<T> extends SimpleOrientRepository<T> {

    /** The default cluster name for repository. */
    protected final String cluster;
    
    public ClusteredOrientRepository(OrientOperations operations, Class<T> domainClass, String cluster, Class<?> repositoryInterface) {
        super(operations, domainClass, repositoryInterface);
        
        this.cluster = cluster;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#save(java.lang.Object)
     */
    @Override
    public <S extends T> S save(S entity) {
        return save(entity, cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#save(java.lang.Iterable)
     */
    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return save(entities, cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#findAll()
     */
    @Override
    public List<T> findAll() {
        return findAll(cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#count()
     */
    @Override
    public long count() {
        return count(cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#deleteAll()
     */
    @Override
    public void deleteAll() {
        deleteAll(cluster);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(Sort sort) {
        return findAll(cluster, sort);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.support.SimpleOrientRepository#findAll(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
