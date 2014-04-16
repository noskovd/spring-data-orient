package org.springframework.data.orient.object.person;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class PrepareDatabase {

    public static void main(String[] args) {
        
        @SuppressWarnings("resource")
        OObjectDatabaseTx db = new OObjectDatabaseTx("local:D:/orientdb/spring-data-test").open("admin", "admin");
        
        try {
            db.getMetadata().getSchema().generateSchema(Person.class);
        } finally {
            db.close();
        }
    }
}
