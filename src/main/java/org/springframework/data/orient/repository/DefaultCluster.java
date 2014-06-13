package org.springframework.data.orient.repository;

/**
 * Default implementation of the {@link Cluster}.
 * 
 * @author Dzmitry_Naskou
 */
public final class DefaultCluster implements Cluster {
    
    /** The cluster name. */
    private final String clusterName;
    
    /**
     * Instantiates a new default cluster with the given name.
     *
     * @param clusterName the cluster name
     */
    public DefaultCluster(String clusterName) {
        super();
        this.clusterName = clusterName;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.Cluster#getName()
     */
    @Override
    public String getName() {
        return clusterName;
    }
}
