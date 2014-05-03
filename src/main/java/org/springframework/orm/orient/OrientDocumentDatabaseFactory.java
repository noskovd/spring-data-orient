package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

/**
 * A specific factory for creating OrientDocumentDatabaseFactory objects that handle {@link ODatabaseDocumentTx}.
 * 
 * @author Dzmitry_Naskou
 * @see ODatabaseDocumentTx
 */
public class OrientDocumentDatabaseFactory extends AbstractOrientDatabaseFactory<ODatabaseDocumentTx> {

    /** The database. */
    private ODatabaseDocumentTx db;

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#doCreatePool()
     */
    @Override
    protected ODatabasePoolBase<ODatabaseDocumentTx> doCreatePool() {
        return new ODatabaseDocumentPool(getUrl(), getUsername(), getPassword());
    }

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#openDatabase()
     */
    @Override
    public ODatabaseDocumentTx openDatabase() {
        db = pool.acquire();
        return db;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#db()
     */
    @Override
    public ODatabaseDocumentTx db() {
        return (ODatabaseDocumentTx) super.db();
    }

    /* (non-Javadoc)
     * @see org.springframework.orm.orient.AbstractOrientDatabaseFactory#newDatabase()
     */
    @Override
    protected ODatabaseDocumentTx newDatabase() {
        return new ODatabaseDocumentTx(getUrl());
    }
}   
