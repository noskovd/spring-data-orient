package org.springframework.data.orient.repository.query;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.data.repository.query.Parameter;
import org.springframework.data.repository.query.Parameters;

public class OrientParameters extends Parameters<OrientParameters, Parameter> {

    public OrientParameters(List<Parameter> originals) {
        super(originals);
    }

    public OrientParameters(Method method) {
        super(method);
    }

    @Override
    protected Parameter createParameter(MethodParameter parameter) {
        return null;
    }

    @Override
    protected OrientParameters createFrom(List<Parameter> parameters) {
        return new OrientParameters(parameters);
    }
}
