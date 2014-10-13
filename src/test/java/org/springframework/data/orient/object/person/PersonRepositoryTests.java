package org.springframework.data.orient.object.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
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
public class PersonRepositoryTests extends AbstractTestNGSpringContextTests {

    @Autowired
    PersonRepository repository;
    
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
        }

        Address esenina = operations.command("insert into Address (country, city, street) values ('Belarus', 'Minsk', 'Esenina')");
        
        operations.command("insert into Person (firstName, lastName, active, address) values (?, ?, ?, ?)", "Dzmitry", "Naskou", true, esenina);
        operations.command("insert into Person (firstName, lastName, active) values ('Koby', 'Eliot', true)");
        operations.command("insert into Person (firstName, lastName, active) values ('Ronny', 'Carlisle', true)");
        operations.command("insert into Person (firstName, lastName, active) values ('Jameson', 'Matthew', true)");
        operations.command("insert into Person (firstName, lastName, active) values ('Roydon', 'Brenden', false)");
    }
    
    @Test
    public void repositoryAutowiring() {
        Assert.assertNotNull(repository);
    }
    
    @Test
    public void savePerson() {
        Person person = new Person();
        person.setFirstName("Jay");
        person.setLastName("Miner");
        
        String rid = repository.save(person).getRid();
        
        Person result = repository.findOne(rid);
        
        Assert.assertEquals(result.getFirstName(), person.getFirstName());
        Assert.assertEquals(result.getLastName(), person.getLastName());
    }
    
    @Test
    public void findAllPersons() {
        Assert.assertFalse(repository.findAll().isEmpty());
    }
    
    @Test
    public void countPerson() {
        Assert.assertEquals(repository.count(), 5);
    }

    @Test
    public void countByFirstName() {
        Assert.assertEquals(repository.countByFirstName("Dzmitry"), Long.valueOf(1));
    }
    
    @Test
    public void findByFirstName() {
        Assert.assertFalse(repository.findByFirstName("Dzmitry").isEmpty());
    }
    
    @Test
    public void findByFirstNamePage() {
        for (Person person : repository.findByFirstName("Dzmitry", new PageRequest(1, 5)).getContent()) {
            Assert.assertEquals(person.getFirstName(), "Dzmitry");
        }
    }
    
    @Test
    public void findByFirstNameLike() {
        for (Person person : repository.findByFirstNameLike("Dzm%")) {
            Assert.assertTrue(person.getFirstName().startsWith("Dzm"));
        }
    }
    
    @Test
    public void findByLastName() {
        Assert.assertFalse(repository.findByLastName("Naskou").isEmpty());
    }
    
    @Test
    public void printFindByLastName() {
        for (Person person : repository.findByLastName("Naskou")) {
            Assert.assertEquals(person.getLastName(), "Naskou");
        }
    }
    
    @Test
    public void findByLastNameLike() {
        for (Person person : repository.findByLastNameLike("Na%")) {
            Assert.assertTrue(person.getLastName().startsWith("Na"));
        }
    }

    @Test
    public void findByFirstNameAndLastName() {
        for (Person person : repository.findByFirstNameOrLastName("Dzmitry", "Naskou")) {
            Assert.assertTrue(person.getFirstName().equals("Dzmitry") && person.getLastName().equals("Naskou"));
        }
    }

    @Test
    public void findByFirstNameOrLastName() {
        for (Person person : repository.findByFirstNameOrLastName("Dzmitry", "Eliot")) {
            Assert.assertTrue(person.getFirstName().equals("Dzmitry") || person.getLastName().equals("Eliot"));
        }
    }
    
    @Test
    public void findByActiveIsTrue() {
        for (Person person : repository.findByActiveIsTrue()) {
            Assert.assertTrue(person.getActive());
        }
    }
    
    @Test
    public void findByActiveIsFalse() {
        for (Person person : repository.findByActiveIsFalse()) {
            Assert.assertFalse(person.getActive());
        }
    }
    
    @Test
    public void findByCityTest() {
        List<Person> persons = repository.findByAddress_City("Minsk");
        
        Assert.assertFalse(persons.isEmpty());
        
        for (Person person : persons) {
            Assert.assertEquals(person.getAddress().getCity(), "Minsk");
        }
    }
}
