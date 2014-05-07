package org.springframework.data.orient.object.repository.support;

import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.support.SimpleOrientRepository;

/**
 * Default implementation of the {@link org.springframework.data.repository.PagingAndSortingRepository} interface for Orient Object Database.
 * 
 * @author Dzmitry_Naskou
 * @param <T> the type of the entity to handle
 */
public class SimpleOrientObjectRepository<T> extends SimpleOrientRepository<T> implements OrientObjectRepository<T> {

    /**
     * Instantiates a new {@link SimpleOrientObjectRepository} from the given {@link OrientOperations} and domain class.
     *
     * @param operations the orient operations
     * @param domainClass the domain class
     */
    public SimpleOrientObjectRepository(OrientOperations operations, Class<T> domainClass) {
        super(operations, domainClass);
    }
}
