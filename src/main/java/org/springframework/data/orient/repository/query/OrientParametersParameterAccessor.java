package org.springframework.data.orient.repository.query;

import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.ParametersParameterAccessor;

public class OrientParametersParameterAccessor extends ParametersParameterAccessor implements OrientParameterAccessor {

    public OrientParametersParameterAccessor(Parameters<?, ?> parameters, Object[] values) {
        super(parameters, values);
    }
}
