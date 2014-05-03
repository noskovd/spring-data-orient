package org.springframework.orm.orient;

import com.orientechnologies.orient.core.db.ODatabaseComplex;
import com.orientechnologies.orient.core.tx.OTransaction;

/**
 * The specific Orient Transaction.
 * 
 * @author Dzmitry_Naskou
 */
public class OrientTransaction {

    /** The orient tx object. */
    private OTransaction tx;

    /** The database. */
    private ODatabaseComplex<?> database;

    public OTransaction getTx() {
        return tx;
    }

    public void setTx(OTransaction tx) {
        this.tx = tx;
    }

    public ODatabaseComplex<?> getDatabase() {
        return database;
    }

    public void setDatabase(ODatabaseComplex<?> database) {
        this.database = database;
    }
}

