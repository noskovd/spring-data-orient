package org.springframework.data.orient.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The Orient specific extension of {@link org.springframework.data.repository.Repository}.
 *
 * @author Dzmitry_Naskou
 * @param <T> the generic type
 */
@NoRepositoryBean
public interface OrientRepository<T> extends PagingAndSortingRepository<T, String> {
    
    <S extends T> S save(S entity, String cluster);
    
    long count(String cluster);
    
    /**
     * Gets the domain class for repository.
     *
     * @return the domain class
     */
    Class<T> getDomainClass();
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    @Override
    List<T> findAll();
    
    /**
     * Returns all instances of the type with the given cluster.
     *
     * @param cluster the cluster name
     * @return the list
     */
    List<T> findAll(String cluster);
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    List<T> findAll(Sort sort);
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll(java.lang.Iterable)
     */
    @Override
    List<T> findAll(Iterable<String> ids);
    
    void deleteAll(String cluster);
}
