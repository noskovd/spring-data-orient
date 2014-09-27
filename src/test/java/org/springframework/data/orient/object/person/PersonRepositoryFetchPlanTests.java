package org.springframework.data.orient.object.person;

import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PersonRepositoryFetchPlanTests extends AbstractTestNGSpringContextTests {

    @Autowired
    OrientObjectDatabaseFactory dbf;

    @Autowired
    PersonRepository repository;

    @BeforeMethod
    public void before() {
        dbf.db().getEntityManager().registerEntityClass(Person.class);
        dbf.db().getEntityManager().registerEntityClass(Address.class);
        dbf.db().getEntityManager().registerEntityClass(Employee.class);
    }
    @Test
    public void saveTest() {
        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Okrestino");

        Person person = new Person();
        person.setFirstName("Aliaksei");
        person.setLastName("Zhynhiarouski");
        person.setAddress(address);

        Assert.assertNotNull(repository.save(person).getRid());
    }

    @Test(dependsOnMethods = "saveTest")
    public void findByCityTest2() {
        System.out.println(repository.findByAddress_Country("Belarus").get(0));
    }

}
