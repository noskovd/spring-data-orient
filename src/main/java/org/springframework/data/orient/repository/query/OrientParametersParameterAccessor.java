package org.springframework.data.orient.repository.query;

import org.springframework.data.orient.repository.Cluster;
import org.springframework.data.repository.query.ParametersParameterAccessor;

public class OrientParametersParameterAccessor extends ParametersParameterAccessor implements OrientParameterAccessor {

    private final OrientParameters parameters;
    
    private final Object[] values;
    
    public OrientParametersParameterAccessor(OrientParameters parameters, Object[] values) {
        super(parameters, values);
        
        this.parameters = parameters;
        this.values = values;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.query.OrientParameterAccessor#getCluster()
     */
    @Override
    public Cluster getCluster() {
        if (!parameters.hasClusterParameter()) {
            return null;
        }
        
        return (Cluster) values[parameters.getClusterIndex()];
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.query.OrientParameterAccessor#getClusterName()
     */
    @Override
    public String getClusterName() {
        Cluster cluster = getCluster();
                
        return cluster == null ? null : cluster.getName();
    }
}
