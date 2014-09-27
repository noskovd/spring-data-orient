package org.springframework.data.orient.object;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.core.OrientObjectTemplate;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.orm.orient.OrientTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class OrientObjectTestConfiguration {

    @Bean
    public OrientObjectDatabaseFactory factory() {
        OrientObjectDatabaseFactory factory =  new OrientObjectDatabaseFactory();
        
        factory.setUrl("plocal:test/spring-data-test");
        factory.setUsername("admin");
        factory.setPassword("admin");
        
        return factory;
    }
    
    @Bean
    public OrientTransactionManager transactionManager() {
        return new OrientTransactionManager(factory());
    }

    @Bean
    @Qualifier("contextTemplate")
    public OrientObjectTemplate objectTemplate() {
        return new OrientObjectTemplate(factory());
    }
}
