package org.springframework.data.orient.repository.object.query;

import org.springframework.data.orient.repository.object.query.OrientQueryExecution.CollectionExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.SingleEntityExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.PagedExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.CountExecution;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;

public abstract class AbstractOrientQuery implements RepositoryQuery {

    private final OrientObjectQueryMethod method;
    
    private final OrientObjectTemplate template;

    public AbstractOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super();
        this.method = method;
        this.template = template;
    }

    public QueryMethod getQueryMethod() {
        return method;
    }

    @Override
    public Object execute(Object[] parameters) {
        return doExecute(getExecution(), parameters);
    }
    
    protected Object doExecute(OrientQueryExecution execution, Object[] values) {
        return execution.execute(this, values);
    }
    
    @SuppressWarnings("rawtypes")
    protected OSQLQuery createQuery(Object[] values) {
        return doCreateQuery(values);
    }
    
    @SuppressWarnings("rawtypes")
    protected OSQLQuery createCountQuery(Object[] values) {
        return doCreateCountQuery(values);
    }
    
    @SuppressWarnings("rawtypes")
    protected abstract OSQLQuery doCreateQuery(Object[] values);
    
    @SuppressWarnings("rawtypes")
    protected abstract OSQLQuery doCreateCountQuery(Object[] values);
    
    protected OrientQueryExecution getExecution() {
        if (method.isCollectionQuery()) {
            return new CollectionExecution(template);
        } else if (isCountQuery()) {
            return new CountExecution(template);
        } else if (method.isPageQuery()) {
            return new PagedExecution(template, method.getParameters());
        } else if (method.isQueryForEntity()) {
            return new SingleEntityExecution(template);
        } 
        
        throw new IllegalArgumentException();
    }
    
    protected abstract boolean isCountQuery();
}
