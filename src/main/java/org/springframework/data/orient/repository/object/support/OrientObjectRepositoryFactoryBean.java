package org.springframework.data.orient.repository.object.support;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.orm.orient.OrientObjectTemplate;

public class OrientObjectRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

    @Autowired
    private OrientObjectTemplate template;

    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        return new OrientObjectRepositoryFactory(template);
    }
}
