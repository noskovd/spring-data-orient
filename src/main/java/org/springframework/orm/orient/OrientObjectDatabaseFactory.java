package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientObjectDatabaseFactory extends AbstractOrientDatabaseFactory {

    private OObjectDatabaseTx db;
    
    private ODatabasePoolBase<OObjectDatabaseTx> pool;

    /* (non-Javadoc)
     * @see org.springframework.orient.AbstractOrientDatabaseFactory#createPool()
     */
    @Override
    protected void createPool() {
        pool = new OObjectDatabasePool(getUrl(), getUsername(), getPassword());
        pool.setup(getMinPoolSize(), getMaxPoolSize());
    }

    @Override
    public OObjectDatabaseTx openDatabase() {
        db = pool.acquire();
        return db;
    }

    public OObjectDatabaseTx db() {
        return (OObjectDatabaseTx) super.db();
    }

    protected OObjectDatabaseTx newDatabase() {
        return new OObjectDatabaseTx(getUrl());
    }
}
