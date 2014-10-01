package org.springframework.data.orient.object.person;

import org.test.data.Address;
import org.test.data.Employee;
import org.test.data.Person;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class PrepareDatabase {

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        try (OObjectDatabaseTx db = new OObjectDatabaseTx("plocal:test/spring-data-test").open("admin", "admin")) {
            db.getMetadata().getSchema().generateSchema(Person.class);
            db.getMetadata().getSchema().generateSchema(Address.class);
            db.getMetadata().getSchema().generateSchema(Employee.class);
        }
    }
}
