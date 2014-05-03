package org.springframework.data.orient.repository;

import java.util.List;

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
}
