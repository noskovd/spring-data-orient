package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class OrientObjectDatabaseFactory extends AbstractOrientDatabaseFactory<OObjectDatabaseTx> {

    private OObjectDatabaseTx db;

    @Override
    protected ODatabasePoolBase<OObjectDatabaseTx> doCreatePool() {
        return new OObjectDatabasePool(getUrl(), getUsername(), getPassword());
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
