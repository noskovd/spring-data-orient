package org.springframework.data.orient.object.repository.support;

import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.support.ClusteredOrientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ClusteredOrientObjectRepository<T> extends ClusteredOrientRepository<T> implements OrientObjectRepository<T> {

    public ClusteredOrientObjectRepository(OrientOperations operations, Class<T> domainClass, String cluster, Class<?> repositoryInterface) {
        super(operations, domainClass, cluster, repositoryInterface);
    }

    @Override
    public T detachAll(T entity) {
        return ((OrientObjectOperations) super.operations).detachAll(entity, true);
    }
}
