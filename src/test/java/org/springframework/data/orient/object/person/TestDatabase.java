package org.springframework.data.orient.object.person;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.test.data.Employee;
import org.test.data.Person;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class TestDatabase {

    
    OObjectDatabaseTx db;
    
    @Before
    @SuppressWarnings("resource")
    public void before() {
        db = new OObjectDatabaseTx("local:D:/orientdb/spring-data-test").open("admin", "admin");
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
            Assert.assertEquals(Person.class, o.getClass().getSuperclass());
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
