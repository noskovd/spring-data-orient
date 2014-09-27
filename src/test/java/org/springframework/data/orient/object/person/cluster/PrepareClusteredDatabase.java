package org.springframework.data.orient.object.person.cluster;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.test.data.Address;
import org.test.data.Person;

public class PrepareClusteredDatabase {

    public static void main(String[] args) {

        try (OObjectDatabaseTx db = new OObjectDatabaseTx("plocal:test/spring-data-test-cluster").open("admin", "admin")) {
            db.getMetadata().getSchema().generateSchema(Person.class);
            db.getMetadata().getSchema().generateSchema(Address.class);

            if (!db.existsCluster("person_temp")) {
                int id = db.addCluster("person_temp");
                db.getMetadata().getSchema().getClass(Person.class).addClusterId(id);
            }

            db.close();
        }
    }
}
