package org.springframework.data.orient.repository.object.query;

import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;

public class PartTreeOrientQuery extends AbstractOrientQuery {

    public PartTreeOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super(method, template);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateQuery(Object[] values) {
        return null;
    }
}
