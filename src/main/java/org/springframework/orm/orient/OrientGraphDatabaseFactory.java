package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.db.graph.OGraphDatabasePool;

/**
 * Factory for OrientDB graph databases.
 * 
 * @author Harald Wellmann
 * 
 */
@SuppressWarnings("deprecation")
public class OrientGraphDatabaseFactory extends AbstractOrientDatabaseFactory {

    private OGraphDatabase db;
    
    private ODatabasePoolBase<OGraphDatabase> pool;

    @Override
    protected void createPool() {
        pool = new OGraphDatabasePool(getUrl(), getUsername(), getPassword());
        pool.setup(getMinPoolSize(), getMaxPoolSize());
    }

    @Override
    public OGraphDatabase openDatabase() {
        db = pool.acquire();
        return db;
    }

    public OGraphDatabase db() {
        return (OGraphDatabase) super.db();
    }

    protected OGraphDatabase newDatabase() {
        return new OGraphDatabase(getUrl());
    }
}

