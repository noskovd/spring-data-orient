package org.springframework.data.orient.object.person.cluster;


import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.orient.core.OrientObjectOperations;
import org.springframework.data.orient.repository.DefaultCluster;
import org.springframework.orm.orient.OrientObjectDatabaseFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.test.data.Person;
import org.testng.annotations.Test;

@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = PersonClusteredRepositoryTestConfiguration.class)
public class PersonClusteredRepositoryTests extends AbstractTestNGSpringContextTests {

    @Autowired
    OrientObjectDatabaseFactory dbf;

    @Autowired
    PersonClusteredRepository repository;

    @Autowired
    @Qualifier("personClusterTemplate")
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
        System.out.println(repository.findAll("person_temp"));
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
    public void getPersonClusters() {
        OObjectDatabaseTx db = dbf.openDatabase();

        for (int i : db.getMetadata().getSchema().getClass(Person.class).getClusterIds()) {
            System.out.println(i);
        }


        db.close();
    }

    @Test
    public void findByFirstNameByCluster() {
        repository.findByFirstName("Dzmitry", new DefaultCluster("person_temp"));
    }
}
