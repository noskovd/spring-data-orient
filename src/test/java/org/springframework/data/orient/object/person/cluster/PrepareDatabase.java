package org.springframework.data.orient.object.person.cluster;

import org.test.data.Address;
import org.test.data.Person;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class PrepareDatabase {

    public static void main(String[] args) {
        
        @SuppressWarnings("resource")
        OObjectDatabaseTx db = new OObjectDatabaseTx("local:D:/orientdb/spring-data-test-cluster").open("admin", "admin");
        
        try {
            db.getMetadata().getSchema().generateSchema(Person.class);
            db.getMetadata().getSchema().generateSchema(Address.class);
        } finally {
            db.close();
        }
    }
}
