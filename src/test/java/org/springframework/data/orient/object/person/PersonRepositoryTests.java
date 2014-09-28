package org.springframework.data.orient.object.person;

import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Address;
import org.test.data.Employee;
import org.test.data.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = PersonRepositoryTestConfiguration.class)
public class PersonRepositoryTests extends AbstractTestNGSpringContextTests {

    @Autowired
    OrientObjectDatabaseFactory dbf;
    
    @Autowired
    PersonRepository repository;

    @BeforeMethod
    public void before() {
        dbf.db().getEntityManager().registerEntityClass(Person.class);
        dbf.db().getEntityManager().registerEntityClass(Address.class);
        dbf.db().getEntityManager().registerEntityClass(Employee.class);
        saveTest();
    }

    public void saveTest() {
        Person person = new Person();
        person.setFirstName("Dzmitry");
        person.setLastName("Naskou");

        Assert.assertNotNull(repository.save(person).getRid());
    }

    @Test
    public void checkRepository() {
        Assert.assertNotNull(repository);
    }

    @Test
    public void countTest() {
        Assert.assertNotNull(repository.count());
    }

    @Test
    public void countByFirstName() {
        Assert.assertTrue(repository.countByFirstName("Dzmitry") > 0);
    }

    @Test(enabled = false)
    public void deleteAllTest() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }
    
    @Test
    public void findAllTest() {
        Assert.assertTrue(repository.findAll().iterator().hasNext());
    }
    
    @Test
    public void printFindAll() {
        for (Person person : repository.findAll()) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }
    }
    
    @Test
    public void findByFirstName() {
        Assert.assertFalse(repository.findByFirstName("Dzmitry").isEmpty());
    }
    
    @Test
    public void findByFirstNamePage() {
        System.out.println(repository.findByFirstName("Dzmitry", new PageRequest(1, 5)));
    }
    
    @Test
    public void findByFirstNameLike() {
        for (Person person : repository.findByFirstNameLike("Dzm%")) {
            System.out.println(person.getFirstName());
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
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }
    }
    
    @Test
    public void findByLastNameLike() {
        Assert.assertFalse(repository.findByLastNameLike("Na%").isEmpty());
    }

    @Test
    public void findByFirstNameAndLastName() {
        Assert.assertFalse(repository.findByFirstNameAndLastName("Dzmitry", "Naskou").isEmpty());
    }

    @Test
    public void findByFirstNameOrLastName() {
        Assert.assertFalse(repository.findByFirstNameOrLastName("Dzmitry", "Naskou").isEmpty());
    }
}
