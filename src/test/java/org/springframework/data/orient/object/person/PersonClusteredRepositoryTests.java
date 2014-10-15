package org.springframework.data.orient.object.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.orient.core.OrientOperations;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Address;
import org.test.data.Employee;
import org.test.data.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orientechnologies.orient.core.entity.OEntityManager;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@TransactionConfiguration(defaultRollback = true)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
public class PersonClusteredRepositoryTests extends AbstractTestNGSpringContextTests {

    @Autowired
    PersonRepository repository;
    
    @Autowired
    PersonTmpRepository tmpRepository;
    
    @Autowired
    OrientObjectDatabaseFactory factory;
    
    @Autowired
    OrientOperations operations;
    
    @BeforeClass
    public void before() {
        try (OObjectDatabaseTx db = factory.openDatabase()) {
            OEntityManager manager = db.getEntityManager();
            manager.registerEntityClass(Person.class);
            manager.registerEntityClass(Address.class);
            manager.registerEntityClass(Employee.class);
            
            db.getMetadata().getSchema().getClass(Person.class).addCluster("person_tmp");
            db.getMetadata().getSchema().getClass(Person.class).addCluster("person_history");
        }
        
        operations.command("insert into cluster:person (firstName, lastName, active) values ('Dzmitry', 'Naskou', true)");
        operations.command("insert into cluster:person (firstName, lastName, active) values ('Koby', 'Eliot', true)");
        operations.command("insert into cluster:person_tmp (firstName, lastName, active) values ('Ronny', 'Carlisle', true)");
        operations.command("insert into cluster:person_tmp (firstName, lastName, active) values ('Jameson', 'Matthew', true)");
        operations.command("insert into cluster:person_history (firstName, lastName, active) values ('Roydon', 'Brenden', false)");
    }
    
    @Test
    public void savePersonToTmpCluster() {
        Person person = new Person();
        person.setFirstName("Jay");
        person.setLastName("Miner");
        
        String rid = tmpRepository.save(person).getRid();
        
        Person result = tmpRepository.findOne(rid);
        
        Assert.assertEquals(result.getFirstName(), person.getFirstName());
        Assert.assertEquals(result.getLastName(), person.getLastName());
    }
    
    @Test
    public void repositoryAutowiring() {
        Assert.assertNotNull(repository);
    }
    
    @Test
    public void tmpRepositoryAutowiring() {
        Assert.assertNotNull(tmpRepository);
    }
    
    @Test
    public void countPerson() {
        Assert.assertEquals(repository.count(), 5);
    }
    
    @Test
    public void countPersonTmp() {
        Assert.assertEquals(tmpRepository.count(), 2);
    }
}
