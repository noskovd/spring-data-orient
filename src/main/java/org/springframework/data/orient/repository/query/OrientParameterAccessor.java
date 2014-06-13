package org.springframework.data.orient.repository.query;

import org.springframework.data.orient.repository.Cluster;
import org.springframework.data.repository.query.ParameterAccessor;

public interface OrientParameterAccessor extends ParameterAccessor {

    Cluster getCluster();
    
    String getClusterName();
}
