package org.springframework.data.orient.repository.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.orient.repository.Cluster;
import org.springframework.data.repository.query.Parameter;

public class OrientParameter extends Parameter {
 
    static final List<Class<?>> ORIENT_TYPES = Arrays.asList(Pageable.class, Sort.class, Cluster.class);
    
    protected OrientParameter(MethodParameter parameter) {
        super(parameter);
    }

    @Override
    public boolean isSpecialParameter() {
        return super.isSpecialParameter() || ORIENT_TYPES.contains(getType());
    }
    
    boolean isCluster() {
        return Cluster.class.isAssignableFrom(getType());
    }
}
