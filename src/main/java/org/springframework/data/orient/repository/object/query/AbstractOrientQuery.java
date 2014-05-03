package org.springframework.data.orient.repository.object.query;

import org.springframework.data.orient.repository.object.query.OrientQueryExecution.CollectionExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.SingleEntityExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.PagedExecution;
import org.springframework.data.orient.repository.object.query.OrientQueryExecution.CountExecution;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;

/**
 * The base class to implement {@link RepositoryQuery}s for OrientDB.
 */
public abstract class AbstractOrientQuery implements RepositoryQuery {

    /** The query method. */
    private final OrientObjectQueryMethod method;
    
    /** The object template. */
    private final OrientObjectTemplate template;

    /**
     * Instantiates a new {@link AbstractOrientQuery}.
     *
     * @param method the query method
     * @param template the orient object template
     */
    public AbstractOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super();
        this.method = method;
        this.template = template;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.query.RepositoryQuery#getQueryMethod()
     */
    public QueryMethod getQueryMethod() {
        return method;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.query.RepositoryQuery#execute(java.lang.Object[])
     */
    @Override
    public Object execute(Object[] parameters) {
        return doExecute(getExecution(), parameters);
    }
    
    /**
     * Do execute.
     *
     * @param execution the execution
     * @param values the values
     * @return the object
     */
    protected Object doExecute(OrientQueryExecution execution, Object[] values) {
        return execution.execute(this, values);
    }
    
    /**
     * Creates the orient query.
     *
     * @param values the parameters for query
     * @return the OSQL query
     */
    @SuppressWarnings("rawtypes")
    protected OSQLQuery createQuery(Object[] values) {
        return applyFetchPlan(doCreateQuery(values));
    }
    
    /**
     * Creates the count query.
     *
     * @param values the parameters for query
     * @return the OSQL query
     */
    @SuppressWarnings("rawtypes")
    protected OSQLQuery createCountQuery(Object[] values) {
        return doCreateCountQuery(values);
    }
    
    /**
     * Do create query for specific source.
     *
     * @param values the parameters for query
     * @return the OSQL query
     */
    @SuppressWarnings("rawtypes")
    protected abstract OSQLQuery doCreateQuery(Object[] values);
    
    /**
     * Do create count query for specific source.
     *
     * @param values the parameters for query
     * @return the OSQL query
     */
    @SuppressWarnings("rawtypes")
    protected abstract OSQLQuery doCreateCountQuery(Object[] values);
    
    /**
     * Gets the execution for query.
     *
     * @return the execution
     */
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
    
    /**
     * Checks if is count query.
     *
     * @return true, if is count query
     */
    protected abstract boolean isCountQuery();
    
    @SuppressWarnings("rawtypes")
    private OSQLQuery applyFetchPlan(OSQLQuery query) {
        String fetchPlan = method.getFetchPlan();
        
        if (fetchPlan != null) {
            query.setFetchPlan(fetchPlan);
        }
        
        return query;
    }
}
