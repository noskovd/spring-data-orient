package org.springframework.data.orient.repository.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.data.orient.repository.OrientCluster;
import org.springframework.data.orient.repository.OrientSource;
import org.springframework.data.repository.query.Parameter;

public class OrientParameter extends Parameter {
 
    static final List<Class<?>> ORIENT_TYPES = Arrays.asList(OrientSource.class, OrientCluster.class);
    
    static final List<Class<?>> ORIENT_SOURCE_TYPES = Arrays.asList(OrientSource.class, OrientCluster.class);
    
    protected OrientParameter(MethodParameter parameter) {
        super(parameter);
    }

    @Override
    public boolean isSpecialParameter() {
        return super.isSpecialParameter() || ORIENT_TYPES.contains(getType());
    }
    
    @Deprecated
    boolean isCluster() {
        return OrientCluster.class.isAssignableFrom(getType());
    }
    
    /**
     * Checks if the parameter is the source.
     *
     * @return true, if it's source
     */
    boolean isSource() {
        return OrientSource.class.isAssignableFrom(getType());
    }
}
