package org.springframework.data.orient.object.person;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class TestDatabase {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        
        @SuppressWarnings("resource")
        OObjectDatabaseTx db = new OObjectDatabaseTx("local:D:/orientdb/spring-data-test").open("admin", "admin");
        
        try {
            db.getEntityManager().registerEntityClass(Person.class);
            
            for (Object o : db.query(new OSQLSynchQuery("select from person"))) {
                System.out.println(o.getClass().getSuperclass());
            }
            
            System.out.println("================================================");
            
            for (Object o : new OSQLSynchQuery("select from person").run()) {
                System.out.println(o.getClass());
            }
            
        } finally {
            db.close();
        }
    }
}
