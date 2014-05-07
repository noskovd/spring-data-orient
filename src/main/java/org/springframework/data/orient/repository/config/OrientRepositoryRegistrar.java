package org.springframework.data.orient.repository.config;

import java.lang.annotation.Annotation;

import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

/**
 * {@link ImportBeanDefinitionRegistrar} to enable {@link EnableOrientRepositories} annotation.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientRepositoryRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport#getAnnotation()
     */
    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableOrientRepositories.class;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport#getExtension()
     */
    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new OrientRepositoryConfigExtension();
    }
}
