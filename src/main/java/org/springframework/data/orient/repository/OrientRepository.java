package org.springframework.data.orient.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface OrientRepository<T> extends PagingAndSortingRepository<T, String> {
    
    /**
     * Gets the domain class for repository.
     *
     * @return the domain class
     */
    Class<T> getDomainClass();
}
