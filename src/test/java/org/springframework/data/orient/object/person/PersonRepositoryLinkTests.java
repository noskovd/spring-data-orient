package org.springframework.data.orient.object.person;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Address;
import org.test.data.Employee;
import org.test.data.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = PersonRepositoryTestConfiguration.class)
public class PersonRepositoryLinkTests {

    @Autowired
    OrientObjectDatabaseFactory dbf;
    
    @Before
    public void before() {
        dbf.db().getEntityManager().registerEntityClass(Person.class);
        dbf.db().getEntityManager().registerEntityClass(Address.class);
        dbf.db().getEntityManager().registerEntityClass(Employee.class);
    }
    
    @Autowired
    PersonRepository repository;
    
    @Ignore
    @Test
    public void saveTest() {
        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Nezavisimosti");
        
        Person person = new Person();
        person.setFirstName("Anton");
        person.setLastName("Siamashka");
        person.setAddress(address);
        
        Assert.assertNotNull(repository.save(person).getRid());
    }
    
    @Test
    public void findByCityTest() {
        Assert.assertTrue(repository.findByAddress_City("Minsk").size() > 0);
    }
    
    @Test
    public void findByCityTest2() {
        System.out.println(repository.findByAddress_City("Minsk").get(0));
    }

}
