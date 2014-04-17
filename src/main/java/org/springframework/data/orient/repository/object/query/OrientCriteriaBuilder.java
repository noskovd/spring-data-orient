package org.springframework.data.orient.repository.object.query;

import javax.persistence.criteria.CriteriaBuilder;

public interface OrientCriteriaBuilder extends CriteriaBuilder {

    OrientCriteriaQuery<Object> createQuery();
}
