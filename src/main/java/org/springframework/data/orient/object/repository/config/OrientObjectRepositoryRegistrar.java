package org.springframework.data.orient.object.repository.config;

import java.lang.annotation.Annotation;

import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

/**
 * {@link ImportBeanDefinitionRegistrar} to enable {@link EnableOrientObjectRepositories} annotation.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientObjectRepositoryRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport#getAnnotation()
     */
    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableOrientObjectRepositories.class;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport#getExtension()
     */
    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new OrientObjectRepositoryConfigExtension();
    }
}
