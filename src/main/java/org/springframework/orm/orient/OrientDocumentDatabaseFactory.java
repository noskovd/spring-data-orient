package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class OrientDocumentDatabaseFactory extends AbstractOrientDatabaseFactory {

    private ODatabaseDocumentTx db;
    private ODatabasePoolBase<ODatabaseDocumentTx> pool;

    @Override
    protected void createPool() {
        pool = new ODatabaseDocumentPool(getUrl(), getUsername(), getPassword());
        pool.setup(getMinPoolSize(), getMaxPoolSize());        
    }


    @Override
    public ODatabaseDocumentTx openDatabase() {
        db = pool.acquire();
        return db;
    }
    
    public ODatabaseDocumentTx db() {
        return (ODatabaseDocumentTx) super.db();
    }
    

    protected ODatabaseDocumentTx newDatabase() {
        return new ODatabaseDocumentTx(getUrl());
    }
}   
