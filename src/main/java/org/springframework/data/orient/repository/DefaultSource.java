package org.springframework.data.orient.repository;

/**
 * Default implementation of {@link OrientSource}.
 * 
 * @author Dzmitry_Naskou
 */
public final class DefaultSource implements OrientSource {

    /** The source type. */
    private final SourceType type;
    
    /** The source name. */
    private final String name;
    
    /**
     * Instantiates a new default source with the given {@link SourceType} and source name.
     *
     * @param type the source type
     * @param name the source name
     */
    public DefaultSource(SourceType type, String name) {
        super();
        this.type = type;
        this.name = name;
    }
    
    /**
     * Instantiates a new source with the given {@link OrientCluster}.
     *
     * @param cluster the cluster
     */
    public DefaultSource(OrientCluster cluster) {
        this(SourceType.CLUSTER, cluster.getName());
    }
    
    /**
     * Instantiates a new cluster source with the given cluster name.
     *
     * @param clusterName the cluster name
     */
    public DefaultSource(String clusterName) {
        this(SourceType.CLUSTER, clusterName);
    }
    
    /**
     * Instantiates a new class source with the given class.
     *
     * @param domainClass the domain class
     */
    public DefaultSource(Class<?> domainClass) {
        this(SourceType.CLASS, domainClass.getSimpleName());
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.Source#getSourceType()
     */
    @Override
    public SourceType getSourceType() {
        return type;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.Source#getName()
     */
    @Override
    public String getName() {
        return name;
    }
}
