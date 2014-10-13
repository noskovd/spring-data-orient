package org.springframework.data.orient.repository.support;

import java.io.Serializable;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.object.repository.support.SimpleOrientObjectRepository;
import org.springframework.data.orient.repository.SourceType;
import org.springframework.data.orient.repository.annotation.Cluster;
import org.springframework.data.orient.repository.annotation.Source;
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
        String cluster = getCustomCluster(metadata);

        if (isObjectRepository(metadata.getRepositoryInterface())) {
            if (cluster != null) {
                return new SimpleOrientObjectRepository(operations, javaType, cluster, repositoryInterface);
            } else {
                return new SimpleOrientObjectRepository(operations, javaType, repositoryInterface);
            }
        } else {
            if (cluster != null) {
                return new SimpleOrientRepository(operations, javaType, cluster, repositoryInterface);
            } else {
                return new SimpleOrientRepository(operations, javaType, repositoryInterface);
            }
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
     * Returns whether the given repository interface requires a {@link org.springframework.data.orient.object.repository.OrientObjectRepository} specific implementation to be chosen.
     *
     * @param repositoryInterface the repository interface
     * @return true, if is repository assignable from OrientObjectRepository
     */
    private boolean isObjectRepository(Class<?>  repositoryInterface) {
        return OrientObjectRepository.class.isAssignableFrom(repositoryInterface);
    }

    /**
     * Get Custom Cluster Name.
     * Method looks for {@link Source} and {@link Cluster} annotation.
     *
     * If {@link Source} is not null and {@link org.springframework.data.orient.repository.annotation.Source#type()} equals to
     * {@link org.springframework.data.orient.repository.SourceType#CLUSTER} then returns {@link org.springframework.data.orient.repository.annotation.Source#value()}
     *
     * If {@link Cluster} is not null then returns {@link org.springframework.data.orient.repository.annotation.Cluster#value()}
     *
     * @param metadata
     * @return cluster name or null if it's not defined
     */
    private String getCustomCluster(RepositoryMetadata metadata){
        Class<?> repositoryInterface = metadata.getRepositoryInterface();

        Source source = AnnotationUtils.getAnnotation(repositoryInterface, Source.class);
        if(source != null && SourceType.CLUSTER.equals(source.type())){
            return source.value();
        }

        Cluster cluster = AnnotationUtils.getAnnotation(repositoryInterface, Cluster.class);
        if (cluster != null){
            return cluster.value();
        }
        return null;
    }
}
