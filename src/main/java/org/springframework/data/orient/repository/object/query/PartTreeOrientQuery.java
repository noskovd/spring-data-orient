package org.springframework.data.orient.repository.object.query;

import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;

public class PartTreeOrientQuery extends AbstractOrientQuery {

    @SuppressWarnings("unused")
    private final OrientObjectTemplate template;
    
    @SuppressWarnings("unused")
    private final Class<?> domainClass;
    
    public PartTreeOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super(method, template);
        
        this.template = template;
        this.domainClass = method.getEntityInformation().getJavaType();
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateQuery(Object[] values) {
        return null;
    }
}
