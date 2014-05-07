package org.springframework.data.orient.repository.object.support;

import java.io.Serializable;

import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.repository.object.query.OrientObjectQueryLookupStrategy;
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
public class OrientObjectRepositoryFactory extends RepositoryFactorySupport {

    /** The orient template. */
    private final OrientOperations operations;
    
    /**
     * Instantiates a new {@link OrientObjectRepositoryFactory}.
     *
     * @param operations the orient object template
     */
    public OrientObjectRepositoryFactory(OrientOperations operations) {
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
        
        return new SimpleOrientObjectRepository(operations, entityInformation.getJavaType());
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return SimpleOrientObjectRepository.class;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getQueryLookupStrategy(org.springframework.data.repository.query.QueryLookupStrategy.Key)
     */
    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key) {
        return OrientObjectQueryLookupStrategy.create(operations, key);
    }
}
