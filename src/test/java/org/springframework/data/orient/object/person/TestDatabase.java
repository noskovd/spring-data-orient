package org.springframework.data.orient.object.person;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.test.data.Employee;
import org.test.data.Person;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class TestDatabase {

    
    OObjectDatabaseTx db;
    
    @BeforeMethod
    @SuppressWarnings("resource")
    public void before() {
        //create before open it
        db = new OObjectDatabaseTx("plocal:test/spring-data-test");
        if (!db.exists()) {
            db.create();
        } else {
            db.open("admin", "admin");
        }
        db.getEntityManager().registerEntityClass(Person.class);
        db.getEntityManager().registerEntityClass(Employee.class);
    }
    
    @After
    public void after() {
        db.close();
    }
    
    @Test
    @SuppressWarnings("rawtypes")
    public void dbQuery() {
        for (Object o : db.query(new OSQLSynchQuery("select from person"))) {
            Assert.assertTrue(Person.class.isAssignableFrom(o.getClass().getSuperclass()));
        }
    }
    
    @Test
    @SuppressWarnings("rawtypes")
    public void queryRun() {
        for (Object o : new OSQLSynchQuery("select from person").run()) {
            Assert.assertEquals(ODocument.class, o.getClass());
        }
    }
    
    @Test
    public void dbQueryByParameter() {
        for (Object person : db.query(new OSQLSynchQuery<Person>("select from person where firstName = ?"), "Dzmitry")) {
            Assert.assertEquals("Dzmitry", ((Person) person).getFirstName());
        }    
    }
    
    @Test
    public void dbQueryByNamedParameter() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("firstName", "Dzmitry");
        
        for (Object person : db.query(new OSQLSynchQuery<Person>("select from person where firstName = :firstName"), params)) {
            Assert.assertEquals("Dzmitry", ((Person) person).getFirstName());
        }
    }
}
