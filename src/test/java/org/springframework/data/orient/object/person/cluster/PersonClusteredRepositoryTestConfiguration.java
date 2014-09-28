package org.springframework.data.orient.object.person.cluster;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.core.OrientObjectTemplate;
import org.springframework.data.orient.repository.config.EnableOrientRepositories;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.orm.orient.OrientTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.test.data.Address;
import org.test.data.Person;

import javax.annotation.PostConstruct;

@Configuration
@EnableTransactionManagement
@EnableOrientRepositories(basePackages = "org.springframework.data.orient.object.person.cluster")
public class PersonClusteredRepositoryTestConfiguration {

    @Bean
    public OrientObjectDatabaseFactory factory() {
        OrientObjectDatabaseFactory factory =  new OrientObjectDatabaseFactory();
        
        factory.setUrl("plocal:test/spring-data-test-cluster");
        factory.setUsername("admin");
        factory.setPassword("admin");
        
        return factory;
    }
    
    @Bean
    @Qualifier("personClusterTemplate")
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
        factory().db().getEntityManager().registerEntityClass(Address.class);
        
        OObjectDatabaseTx db = factory().openDatabase();
        if (!db.existsCluster("person_temp")) {
            int id = db.addCluster("person_temp");
            db.getMetadata().getSchema().getClass(Person.class).addClusterId(id);
        }
        
        db.close();
    }
}
