package org.springframework.data.orient.repository.object.query;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.orient.repository.object.Detach;
import org.springframework.data.orient.repository.object.DetachMode;
import org.springframework.data.orient.repository.object.FetchPlan;
import org.springframework.data.orient.repository.object.Query;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.StringUtils;

/**
 * Orient specific extension of {@link QueryMethod}.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientObjectQueryMethod extends QueryMethod {

    /** The method. */
    private final Method method;
    
    /**
     * Instantiates a new {@link OrientObjectQueryMethod}.
     *
     * @param method the method
     * @param metadata the metadata
     */
    public OrientObjectQueryMethod(Method method, RepositoryMetadata metadata) {
        super(method, metadata);
        this.method = method;
    }
    
    /**
     * Returns whether the method has an annotated query.
     * 
     * @return
     */
    public boolean hasAnnotatedQuery() {
        return getAnnotatedQuery() != null;
    }
    
    /**
     * Returns the query string declared in a {@link Query} annotation or {@literal null} if neither the annotation found
     * nor the attribute was specified.
     *
     * @return the query
     */
    String getAnnotatedQuery() {
        String query = (String) AnnotationUtils.getValue(getQueryAnnotation());
        return StringUtils.hasText(query) ? query : null;
    }
    
    /**
     * Returns the {@link Query} annotation that is applied to the method or {@code null} if none available.
     * 
     * @return
     */
    Query getQueryAnnotation() {
        return method.getAnnotation(Query.class);
    }
    
    FetchPlan getFetchPlanAnnotation() {
        return method.getAnnotation(FetchPlan.class);
    }
    
    String getFetchPlan() {
        String plan = (String) AnnotationUtils.getValue(getFetchPlanAnnotation());
        
        return StringUtils.hasText(plan) ? plan : null;
    }
    
    Detach getDetachAnnotation() {
        return method.getAnnotation(Detach.class);
    }
    
    DetachMode getDetachMode() {
        DetachMode mode = (DetachMode) AnnotationUtils.getValue(getDetachAnnotation());
        
        return mode == null ? DetachMode.NONE : mode;
    }
}
