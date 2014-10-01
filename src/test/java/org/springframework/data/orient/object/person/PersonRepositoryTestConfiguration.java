package org.springframework.data.orient.object.person;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.core.OrientObjectTemplate;
import org.springframework.data.orient.repository.config.EnableOrientRepositories;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.orm.orient.OrientTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.test.data.Employee;
import org.test.data.Person;

@Configuration
@EnableTransactionManagement
@EnableOrientRepositories(basePackages = "org.springframework.data.orient.object.person")
public class PersonRepositoryTestConfiguration {

    @Bean
    public OrientObjectDatabaseFactory factory() {
        OrientObjectDatabaseFactory factory =  new OrientObjectDatabaseFactory();
        
        factory.setUrl("plocal:test/spring-data-test");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }
    
    @Bean
    @Qualifier("personTemplate")
    public OrientObjectTemplate objectTemplate() {
        return new OrientObjectTemplate(factory());
    }
    
    @Bean
    public OrientTransactionManager transactionManager() {
        return new OrientTransactionManager(factory());
    }
    
    @PostConstruct
    public void registerEntities() {
        factory().db().getEntityManager().registerEntityClass(Person.class);
        factory().db().getEntityManager().registerEntityClass(Employee.class);
    }
}
