package org.springframework.data.orient.repository.object.query;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.ParametersParameterAccessor;
import org.springframework.orm.orient.OrientObjectTemplate;

public abstract class OrientQueryExecution {

    protected final OrientObjectTemplate template;

    public OrientQueryExecution(OrientObjectTemplate template) {
        super();
        this.template = template;
    }
    
    public Object execute(AbstractOrientQuery query, Object[] values) {
        return doExecute(query, values);
    }
    
    protected abstract Object doExecute(AbstractOrientQuery query, Object[] values);
    
    static class CollectionExecution extends OrientQueryExecution {

        public CollectionExecution(OrientObjectTemplate template) {
            super(template);
        }

        @Override
        protected Object doExecute(AbstractOrientQuery query, Object[] values) {
            return template.query(query.createQuery(values), values);
        }
    }
    
    static class SingleEntityExecution extends OrientQueryExecution {

        public SingleEntityExecution(OrientObjectTemplate template) {
            super(template);
        }

        @Override
        protected Object doExecute(AbstractOrientQuery query, Object[] values) {
            return template.queryForObject(query.createQuery(values), values);
        }
    }
    
    static class CountExecution extends OrientQueryExecution {

        public CountExecution(OrientObjectTemplate template) {
            super(template);
        }

        @Override
        protected Object doExecute(AbstractOrientQuery query, Object[] values) {
            return template.count(query.createQuery(values), values);
        }
    }
    
    static class PagedExecution extends OrientQueryExecution {

        private final Parameters<?, ?> parameters;
        
        public PagedExecution(OrientObjectTemplate template, Parameters<?, ?> parameters) {
            super(template);
            this.parameters = parameters;
        }

        @Override
        protected Object doExecute(AbstractOrientQuery query, Object[] values) {
            ParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
            
            final Object[] queryParams = prepareForQuery(parameters, values);
            
            Long total = template.count(query.createCountQuery(values), queryParams);
            
            Pageable pageable = accessor.getPageable();
            
            List<Object> content;
            
            if (pageable != null && total > pageable.getOffset()) {
                content = template.query(query.createQuery(values), queryParams);
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
