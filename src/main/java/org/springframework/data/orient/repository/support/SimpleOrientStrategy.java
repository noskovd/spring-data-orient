package org.springframework.data.orient.repository.support;

import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.repository.query.QueryUtils;

public class SimpleOrientStrategy<T> implements OrientStrategy<T> {

    private final OrientOperations operations;
    
    private final Class<T> domainClass;
    
    public SimpleOrientStrategy(OrientOperations operations, Class<T> domainClass) {
        super();
        this.operations = operations;
        this.domainClass = domainClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        return operations.save(entity);
    }

    @Override
    public long count() {
        return operations.countClass(domainClass);
    }

    @Override
    public String getSource() {
        return QueryUtils.toSource(domainClass);
    }
}
