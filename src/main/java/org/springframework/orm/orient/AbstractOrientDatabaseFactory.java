package org.springframework.orm.orient;

import javax.annotation.PostConstruct;

import org.springframework.util.Assert;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseComplex;
import com.orientechnologies.orient.core.db.ODatabasePoolBase;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;

public abstract class AbstractOrientDatabaseFactory<T extends ODatabase> {

    /** Default database username. */
    public static final String DEFAULT_USERNAME = "admin";

    /** Default database password. */
    public static final String DEFAULT_PASSWORD = "admin";

    /** Default minimum pool size. */
    public static final int DEFAULT_MIN_POOL_SIZE = 1;

    /** Default maximum pool size. */
    public static final int DEFAULT_MAX_POOL_SIZE = 20;

    private String username = DEFAULT_USERNAME;

    private String password = DEFAULT_PASSWORD;

    private int minPoolSize = DEFAULT_MIN_POOL_SIZE;

    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;

    private String url;
    
    ODatabasePoolBase<T> pool;

    @PostConstruct
    public void init() {
    	Assert.notNull(url);
        
        ODatabaseComplex<?> db = newDatabase();
        createDatabase(db);
        createPool();
    }

    protected void createPool() {
        pool = doCreatePool();
        pool.setup(minPoolSize, maxPoolSize);
    }
    
    protected abstract ODatabasePoolBase<T> doCreatePool();

    public abstract ODatabaseComplex<?> openDatabase();

    protected abstract ODatabaseComplex<?> newDatabase();

    public ODatabaseComplex<?> db() {
        return ODatabaseRecordThreadLocal.INSTANCE.get().getDatabaseOwner();
    }

    protected void createDatabase(ODatabaseComplex<?> db) {
        if (!getUrl().startsWith("remote:")) {
            if (!db.exists()) {
                db.create();
                db.close();
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
