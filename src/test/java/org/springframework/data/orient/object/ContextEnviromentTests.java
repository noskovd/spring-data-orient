package org.springframework.data.orient.object;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.orm.orient.OrientObjectTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ContextConfiguration(classes = OrientObjectTestConfiguration.class)
public class ContextEnviromentTests {

    @Autowired
    ApplicationContext context;
    
    @Autowired
    OrientObjectDatabaseFactory dbf;
    
    @Autowired
    OrientObjectTemplate template;
    
    @Test
    public void checkApplicationContext() {
        Assert.assertNotNull(context);
    }
    
    @Test
    public void checkOrientObjectDatabaseFactory() {
        Assert.assertNotNull(dbf);
    }
    
    @Test
    public void checkOrientObjectTemplate() {
        Assert.assertNotNull(template); 
    }
    
    @Test
    public void checkTransactionalOrientObjectTemplate() {
        System.out.println(template.getClass()); 
    }

}
