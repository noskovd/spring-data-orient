package org.springframework.data.orient.object.person;

import org.junit.Before;
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
public class PersonRepositoryFetchPlanTests {

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
    
    @Test
    public void findByCityTest2() {
        System.out.println(repository.findByAddress_Country("Belarus").get(0));
    }

}
