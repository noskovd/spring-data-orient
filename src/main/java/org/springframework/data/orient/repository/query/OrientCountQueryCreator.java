package org.springframework.data.orient.repository.query;

import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;

public class OrientCountQueryCreator extends OrientQueryCreator {

    public OrientCountQueryCreator(PartTree tree, String storage, ParameterAccessor parameters) {
        super(tree, storage, parameters);
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.query.OrientQueryCreator#isCountQuery()
     */
    @Override
    public final boolean isCountQuery() {
        return true;
    }
}
