package org.springframework.data.orient.repository.object.query;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.orient.repository.object.Query;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.StringUtils;

public class OrientObjectQueryMethod extends QueryMethod {

    private final Method method;
    
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
}
