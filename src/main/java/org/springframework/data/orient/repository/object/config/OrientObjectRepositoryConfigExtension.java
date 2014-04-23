package org.springframework.data.orient.repository.object.config;

import org.springframework.data.orient.repository.object.support.OrientObjectRepositoryFactoryBean;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;

/**
 * {@link RepositoryConfigurationExtension} for OrientDB.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientObjectRepositoryConfigExtension extends RepositoryConfigurationExtensionSupport {

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtension#getRepositoryFactoryClassName()
     */
    public String getRepositoryFactoryClassName() {
        return OrientObjectRepositoryFactoryBean.class.getName();
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport#getModulePrefix()
     */
    @Override
    protected String getModulePrefix() {
        return "orient";
    }
}
