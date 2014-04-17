package org.springframework.data.orient.repository.object.query;

import javax.persistence.criteria.CriteriaQuery;

public interface OrientCriteriaQuery<T> extends CriteriaQuery<T> {
    
    public String toSQL();
}
