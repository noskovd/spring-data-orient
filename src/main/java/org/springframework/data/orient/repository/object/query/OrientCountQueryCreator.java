package org.springframework.data.orient.repository.object.query;

import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;

public class OrientCountQueryCreator extends OrientQueryCreator {

    public OrientCountQueryCreator(PartTree tree, Class<?> domainClass, ParameterAccessor parameters) {
        super(tree, domainClass, parameters);
    }

    @Override
    public final boolean isCountQuery() {
        return true;
    }
}
