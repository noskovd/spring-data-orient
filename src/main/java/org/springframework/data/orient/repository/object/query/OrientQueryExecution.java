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
            Long total = template.count(query.createCountQuery(values), values);
            
            ParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
            Pageable pageable = accessor.getPageable();
            
            List<Object> content;
            
            if (pageable == null || total > pageable.getOffset()) {
                content = template.query(query.createQuery(values), values);
            } else {
                content = Collections.emptyList();
            }
            
            return new PageImpl<Object>(content, pageable, total);
        }
    }
}
