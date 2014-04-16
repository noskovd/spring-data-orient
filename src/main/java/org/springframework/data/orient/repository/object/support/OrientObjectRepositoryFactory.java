package org.springframework.data.orient.repository.object.support;

import java.io.Serializable;

import org.springframework.data.orient.repository.object.query.OrientObjectQueryLookupStrategy;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.orm.orient.OrientObjectTemplate;

/**
 * Orient specific generic repository factory.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientObjectRepositoryFactory extends RepositoryFactorySupport {

    private final OrientObjectTemplate template;
    
    public OrientObjectRepositoryFactory(OrientObjectTemplate template) {
        super();
        this.template = template;
    }

	@Override
	@SuppressWarnings("unchecked")
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return (EntityInformation<T, ID>) new OrientMetamodelEntityInformation<T>(domainClass);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked"})
    protected Object getTargetRepository(RepositoryMetadata metadata) {
        EntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
        
        return new SimpleOrientObjectRepository(template, entityInformation.getJavaType());
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return SimpleOrientObjectRepository.class;
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key) {
        return OrientObjectQueryLookupStrategy.create(template, key);
    }
}
