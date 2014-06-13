package org.springframework.data.orient.repository;


/**
 * Abstract interface for cluster information.
 * 
 * @author Dzmitry_Naskou
 */
public interface Cluster {

    /**
     * Gets the Orient cluster name.
     *
     * @return the name
     */
    String getName();
}
