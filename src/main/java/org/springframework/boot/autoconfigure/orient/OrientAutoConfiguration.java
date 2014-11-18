package org.springframework.boot.autoconfigure.orient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.data.orient.core.OrientObjectTemplate;
import org.springframework.data.orient.web.config.OrientWebConfigurer;
import org.springframework.orm.orient.AbstractOrientDatabaseFactory;
import org.springframework.orm.orient.OrientDocumentDatabaseFactory;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.orm.orient.OrientTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

@Configuration
@ConditionalOnClass(OObjectDatabaseTx.class)
@EnableConfigurationProperties(OrientProperties.class)
public class OrientAutoConfiguration {

    @Autowired
    private OrientProperties properties;
    
    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    public PlatformTransactionManager transactionManager(AbstractOrientDatabaseFactory factory) {
        return new OrientTransactionManager(factory);
    }
    
    @Bean
    @ConditionalOnMissingBean(OrientObjectDatabaseFactory.class)
    public OrientObjectDatabaseFactory objectDatabaseFactory() {
        OrientObjectDatabaseFactory factory = new OrientObjectDatabaseFactory();
        
        configure(factory);
        
        return factory;
    }
    
    @Bean
    @ConditionalOnMissingClass(OObjectDatabaseTx.class)
    @ConditionalOnMissingBean(OrientDocumentDatabaseFactory.class)
    public OrientDocumentDatabaseFactory documentDatabaseFactory() {
        OrientDocumentDatabaseFactory factory = new OrientDocumentDatabaseFactory();
        
        configure(factory);
        
        return factory;
    }
    
    @Bean
    @ConditionalOnClass(OObjectDatabaseTx.class)
    @ConditionalOnMissingBean(OrientObjectOperations.class)
    public OrientObjectTemplate objectTemplate(OrientObjectDatabaseFactory factory) {
        return new OrientObjectTemplate(factory);
    }
    
    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(OObjectDatabaseTx.class)
    @ConditionalOnMissingBean(OrientWebConfigurer.class)
    public OrientWebConfigurer orientWebConfigurer() {
        return new OrientWebConfigurer();
    }
    
    @SuppressWarnings("rawtypes")
    protected void configure(AbstractOrientDatabaseFactory factory) {
        factory.setUrl(properties.getUrl());
        factory.setUsername(properties.getUsername());
        factory.setPassword(properties.getPassword());
        factory.setMaxPoolSize(properties.getMinPoolSize());
        factory.setMaxPoolSize(properties.getMaxPoolSize());
    }
}
