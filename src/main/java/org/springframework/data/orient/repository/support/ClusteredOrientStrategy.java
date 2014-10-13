package org.springframework.data.orient.repository.support;

import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.repository.query.QueryUtils;

public class ClusteredOrientStrategy<T> implements OrientStrategy<T> {
    
    private final OrientOperations operations;
    
    private final String cluster;
    
    public ClusteredOrientStrategy(OrientOperations operations, String cluster) {
        super();
        this.operations = operations;
        this.cluster = cluster;
    }

    @Override
    public <S extends T> S save(S entity) {
        return operations.save(entity, cluster);
    }

    @Override
    public long count() {
        return operations.countClusterElements(cluster);
    }

    @Override
    public final String getSource() {
        return QueryUtils.clusterToSource(cluster);
    }
}
