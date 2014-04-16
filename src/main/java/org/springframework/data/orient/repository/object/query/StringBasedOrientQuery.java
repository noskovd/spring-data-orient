package org.springframework.data.orient.repository.object.query;

import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class StringBasedOrientQuery extends AbstractOrientQuery {

    @SuppressWarnings("unused")
    private OrientObjectTemplate template;
    
    private final String query;
    
    public StringBasedOrientQuery(String query, OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super(method, template);
        this.query = query;
        this.template = template;
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateQuery(Object[] values) {
        return new OSQLSynchQuery(query);
    }
}
