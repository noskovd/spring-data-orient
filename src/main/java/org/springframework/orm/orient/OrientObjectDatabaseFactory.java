package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * A specific factory for creating OrientObjectDatabase objects that handle {@link OObjectDatabaseTx}.
 * 
 * @author Dzmitry_Naskou
 * @see OObjectDatabaseTx
 */
public class OrientObjectDatabaseFactory extends AbstractOrientDatabaseFactory<OObjectDatabaseTx> {

    /** The database. */
    private OObjectDatabaseTx db;

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#doCreatePool()
     */
    @Override
    protected ODatabasePoolBase<OObjectDatabaseTx> doCreatePool() {
        return new OObjectDatabasePool(getUrl(), getUsername(), getPassword());
    }

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#openDatabase()
     */
    @Override
    public OObjectDatabaseTx openDatabase() {
        db = pool.acquire();
        return db;
    }

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#db()
     */
    @Override
    public OObjectDatabaseTx db() {
        return (OObjectDatabaseTx) super.db();
    }

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#newDatabase()
     */
    @Override
    protected OObjectDatabaseTx newDatabase() {
        return new OObjectDatabaseTx(getUrl());
    }
}
