package org.springframework.data.orient.repository.object.support;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.orm.orient.OrientObjectTemplate;

/**
 * Special adapter for Springs {@link org.springframework.beans.factory.FactoryBean} interface to allow easy setup of
 * repository factories via Spring configuration.
 *
 * @author Dzmitry_Naskou
 * 
 * @param <T> the type of the repository
 * @param <S> the type of the entity to handle
 * @param <ID> the type of the entity identifier to handle
 */
public class OrientObjectRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

    /** The orient template. */
    @Autowired
    private OrientObjectTemplate template;

    /* (non-Javadoc)
     * @see org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport#doCreateRepositoryFactory()
     */
    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        return new OrientObjectRepositoryFactory(template);
    }
}
