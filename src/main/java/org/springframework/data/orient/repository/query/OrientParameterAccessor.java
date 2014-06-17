package org.springframework.data.orient.repository.query;

import org.springframework.data.orient.repository.OrientSource;
import org.springframework.data.repository.query.ParameterAccessor;

public interface OrientParameterAccessor extends ParameterAccessor {

    OrientSource getSource();
}
