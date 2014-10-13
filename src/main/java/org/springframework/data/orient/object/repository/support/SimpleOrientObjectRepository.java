package org.springframework.data.orient.object.repository.support;

import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.support.SimpleOrientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of the {@link org.springframework.data.repository.PagingAndSortingRepository} interface for Orient Object Database.
 * 
 * @author Dzmitry_Naskou
 * @param <T> the type of the entity to handle
 */
@Repository
@Transactional(readOnly = true)
public class SimpleOrientObjectRepository<T> extends SimpleOrientRepository<T> implements OrientObjectRepository<T> {

    /**
     * Instantiates a new {@link SimpleOrientObjectRepository} from the given {@link OrientOperations}, domain class and Repository Interface .
     *
     * @param operations the orient operations
     * @param domainClass the domain class
     * @param repositoryInterface the target repository interface
     */
    public SimpleOrientObjectRepository(OrientOperations operations, Class<T> domainClass, Class<?> repositoryInterface) {
        super(operations, domainClass, repositoryInterface);
    }
    
    public SimpleOrientObjectRepository(OrientOperations operations, Class<T> domainClass, String cluster, Class<?> repositoryInterface) {
        super(operations, domainClass, cluster, repositoryInterface);
    }

    public T detachAll(T entity) {
        return ((OrientObjectOperations) super.operations).detachAll(entity, true);
    }
}
