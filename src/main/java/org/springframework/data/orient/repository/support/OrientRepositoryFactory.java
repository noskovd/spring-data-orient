package org.springframework.data.orient.repository.support;

import java.io.Serializable;

import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.object.repository.support.SimpleOrientObjectRepository;
import org.springframework.data.orient.repository.query.OrientQueryLookupStrategy;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

/**
 * Orient specific generic repository factory.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientRepositoryFactory extends RepositoryFactorySupport {

    /** The orient template. */
    private final OrientOperations operations;
    
    /**
     * Instantiates a new {@link OrientRepositoryFactory}.
     *
     * @param operations the orient object template
     */
    public OrientRepositoryFactory(OrientOperations operations) {
        super();
        this.operations = operations;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getEntityInformation(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return (EntityInformation<T, ID>) new OrientMetamodelEntityInformation<T>(domainClass);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getTargetRepository(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked"})
    protected Object getTargetRepository(RepositoryMetadata metadata) {
        EntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        Class<?> javaType = entityInformation.getJavaType();
        
        if (isObjectRepository(metadata.getRepositoryInterface())) {
            return new SimpleOrientObjectRepository(operations, javaType, repositoryInterface);
        } else {
            return new SimpleOrientRepository(operations, javaType, repositoryInterface);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isObjectRepository(metadata.getRepositoryInterface())) {
            return SimpleOrientObjectRepository.class;
        } else {
            return SimpleOrientRepository.class;
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getQueryLookupStrategy(org.springframework.data.repository.query.QueryLookupStrategy.Key)
     */
    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key) {
        return OrientQueryLookupStrategy.create(operations, key);
    }

    /**
     * Returns whether the given repository interface requires a {@link OrinetObjectRepository} specific implementation to be chosen.
     *
     * @param repositoryInterface the repository interface
     * @return true, if is repository assignable from OrientObjectRepository
     */
    private boolean isObjectRepository(Class<?>  repositoryInterface) {
        return OrientObjectRepository.class.isAssignableFrom(repositoryInterface);
    }
}
