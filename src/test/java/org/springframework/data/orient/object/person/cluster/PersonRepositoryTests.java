package org.springframework.data.orient.object.person.cluster;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.data.orient.repository.annotation.Cluster;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Person;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = PersonRepositoryTestConfiguration.class)
public class PersonRepositoryTests {

    @Autowired
    OrientObjectDatabaseFactory dbf;
    
    @Autowired
    PersonRepository repository;
    
    @Autowired
    OrientObjectOperations operations;
    
    @Test
    public void findAll() {
        System.out.println(repository.findAll());
    }

    @Test
    public void findAll2() {
        for (Person person : repository.findAll()) {
            System.out.println(operations.getClusterNameByRid(person.getRid()));
        }
    }
    
    @Test
    public void savePersonToDefaultClusterTest() {
        Person person = new Person();
        person.setFirstName("Ivan");
        person.setLastName("Ivanou");
        
        repository.save(person);
    }

    @Test
    public void savePersonToClusterTest() {
        Person person = new Person();
        person.setFirstName("Dzmitry");
        person.setLastName("Naskou");
        
        repository.save(person, "person_temp");
    }
    
    @Test
    public void findAllByCluster() {
        System.out.println(repository.findAll("cluster:person_temp"));
    }
    
    @Test
    public void findByLastNameTest() {
        System.out.println(repository.findByLastName("Naskou"));
    }
    
    @Test
    public void checkClasses() {
        OObjectDatabaseTx db = dbf.openDatabase();
        
        for (OClass c : db.getMetadata().getSchema().getClasses()) {
            System.out.println(c);
        }
        
        db.close();
    }
    
    @Test
    public void checkClusters() {
        OObjectDatabaseTx db = dbf.openDatabase();
        
        for (String cluster : db.getClusterNames()) {
            System.out.println(cluster);
        }
        
        db.close();
    }
    
    @Test
    public void checkClusterNameOnInterface() {
        for (Method method : PersonRepository.class.getMethods()) {
            Cluster cluster = AnnotationUtils.findAnnotation(method, Cluster.class);
            if (cluster == null) {
                cluster = AnnotationUtils.findAnnotation(PersonRepository.class, Cluster.class);
            }
            
            System.out.println(cluster);
        }
    }
}
