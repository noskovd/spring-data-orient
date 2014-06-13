package org.springframework.data.orient.repository.query;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.data.orient.object.repository.DetachMode;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.Parameters;

/**
 * Set of classes to contain query execution strategies. 
 * 
 * @author Dzmitry_Naskou
 * 
 */
public abstract class OrientQueryExecution {

    /** The orient object template. */
    protected final OrientOperations operations;

    public OrientQueryExecution(OrientOperations template) {
        super();
        this.operations = template;
    }
    
    /**
     * Executes the given {@link AbstractOrientQuery} with the given {@link Object[]} values.
     *
     * @param query the orient query
     * @param values the parameters values
     * @return the result
     */
    public Object execute(AbstractOrientQuery query, DetachMode mode, Object[] values) {
        return doExecute(query, mode, values);
    }
    
    /**
     * Method to implement by executions.
     *
     * @param query the orient query
     * @param values the parameters values
     * @return the result
     */
    protected abstract Object doExecute(AbstractOrientQuery query, DetachMode mode, Object[] values);
    
    /**
     * Executes the query to return a simple collection of entities.
     * 
     * @author Dzmitry_Naskou
     */
    static class CollectionExecution extends OrientQueryExecution {

        /**
         * Instantiates a new {@link CollectionExecution}.
         *
         * @param template the template
         */
        public CollectionExecution(OrientOperations template) {
            super(template);
        }

        /* (non-Javadoc)
         * @see org.springframework.data.orient.repository.object.query.OrientQueryExecution#doExecute(org.springframework.data.orient.repository.object.query.AbstractOrientQuery, java.lang.Object[])
         */
        @Override
        protected Object doExecute(AbstractOrientQuery query, DetachMode mode, Object[] values) {
            return operations.query(query.createQuery(values), mode, values);
        }
    }
    
    /**
     * Executes a {@link AbstractOrientQuery} to return a single entity.
     * 
     * @author Dzmitry_Naskou
     */
    static class SingleEntityExecution extends OrientQueryExecution {

        /**
         * Instantiates a new {@link SingleEntityExecution}.
         *
         * @param template the template
         */
        public SingleEntityExecution(OrientOperations template) {
            super(template);
        }

        /* (non-Javadoc)
         * @see org.springframework.data.orient.repository.object.query.OrientQueryExecution#doExecute(org.springframework.data.orient.repository.object.query.AbstractOrientQuery, java.lang.Object[])
         */
        @Override
        protected Object doExecute(AbstractOrientQuery query, DetachMode mode, Object[] values) {
            return operations.queryForObject(query.createQuery(values), mode, values);
        }
    }
    
    /**
     * Executes a {@link AbstractOrientQuery} to return a count of entities.
     * 
     * @author Dzmitry_Naskou
     */
    static class CountExecution extends OrientQueryExecution {

        /**
         * Instantiates a new {@link CountExecution}.
         *
         * @param template the template
         */
        public CountExecution(OrientOperations template) {
            super(template);
        }

        /* (non-Javadoc)
         * @see org.springframework.data.orient.repository.object.query.OrientQueryExecution#doExecute(org.springframework.data.orient.repository.object.query.AbstractOrientQuery, java.lang.Object[])
         */
        @Override
        protected Object doExecute(AbstractOrientQuery query, DetachMode mode, Object[] values) {
            return operations.count(query.createQuery(values), values);
        }
    }

    /**
     * Executes the {@link AbstractOrientQuery} to return a {@link org.springframework.data.domain.Page} of entities.
     * 
     * @author Dzmitry_Naskou
     */
    static class PagedExecution extends OrientQueryExecution {

        /** The parameters. */
        private final OrientParameters parameters;
        
        /**
         * Instantiates a new {@link PagedExecution}.
         *
         * @param template the orient object template
         * @param parameters the parameters
         */
        public PagedExecution(OrientOperations template, OrientParameters parameters) {
            super(template);
            this.parameters = parameters;
        }

        /* (non-Javadoc)
         * @see org.springframework.data.orient.repository.object.query.OrientQueryExecution#doExecute(org.springframework.data.orient.repository.object.query.AbstractOrientQuery, java.lang.Object[])
         */
        @Override
        protected Object doExecute(AbstractOrientQuery query, DetachMode mode, Object[] values) {
            OrientParameterAccessor accessor = new OrientParametersParameterAccessor(parameters, values);
            
            final Object[] queryParams = prepareForQuery(parameters, values);
            
            Long total = operations.count(query.createCountQuery(values), queryParams);
            
            Pageable pageable = accessor.getPageable();
            
            List<Object> content;
            
            if (pageable != null && total > pageable.getOffset()) {
                content = operations.query(query.createQuery(values), mode, queryParams);
            } else {
                content = Collections.emptyList();
            }
            
            return new PageImpl<Object>(content, pageable, total);
        }
    }
    
    Object[] prepareForQuery(Parameters<?, ?> parameters, Object[] values) {
        if (parameters.hasPageableParameter()) {
            //TODO: Also implement for Sort parameter!!!
            int index = parameters.getPageableIndex() >= 0 ? parameters.getPageableIndex() : parameters.getSortIndex();
            
            Object[] result = new Object[values.length -1];
            
            System.arraycopy(values, 0, result, 0, index);
            
            if (values.length != index) {
                System.arraycopy(values, index + 1, result, index, values.length - index - 1);
            }
            
            return result;
        }
        
        return values;
    }
    
    @Deprecated
    Object[] prepareForPagedQuery(ParameterAccessor accessor, Parameters<?, ?> parameters, Object[] values) {
        if (parameters.hasSpecialParameter()) {
            int index = parameters.getPageableIndex();
            Pageable pageable = accessor.getPageable();
            
            Object[] result = new Object[values.length + 1];
            
            System.arraycopy(values, 0, result, 0, index);
            
            result[index] = pageable.getPageSize();
            result[index + 1] = pageable.getOffset();
            
            if (values.length != index) {
                System.arraycopy(values, index + 1, result, index + 2, values.length - index - 1);
            }
            
            return result;
        }
        
        return values;
    }
}
