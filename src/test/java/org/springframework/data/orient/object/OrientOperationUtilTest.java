package org.springframework.data.orient.object;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Address;
import org.test.data.Employee;
import org.junit.Test;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ContextConfiguration(classes = OrientObjectTestConfiguration.class)
public class OrientOperationUtilTest {

    @Autowired
    @Qualifier("contextTemplate")
    OrientObjectOperations template;

    @Autowired
    OrientObjectDatabaseFactory dbf;

    @Test
    public void getRidTest(){
        Address address = new Address();
        Assert.assertNull(template.getRid(address));

        address.setId("123");
        Assert.assertEquals("123", template.getRid(address));
    }

    @Test
    public void getRidFromParentTest(){
        Employee employee = new Employee();
        Assert.assertNull(template.getRid(employee));

        employee.setRid("123");
        Assert.assertEquals("123", template.getRid(employee));
    }

    @Test
    public void getRidFromProxy(){
        dbf.db().getEntityManager().registerEntityClass(Employee.class);

        Employee employee = new Employee();
        Employee savedEmployee = template.save(employee);
        Assert.assertNotSame(Employee.class, savedEmployee.getClass());

        Assert.assertEquals(savedEmployee.getRid(), template.getRid(savedEmployee));
    }
}
