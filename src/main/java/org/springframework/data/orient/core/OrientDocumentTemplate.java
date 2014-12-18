package org.springframework.data.orient.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.orient.object.repository.DetachMode;
import org.springframework.orm.orient.OrientDocumentDatabaseFactory;

import com.orientechnologies.common.exception.OException;
import com.orientechnologies.orient.core.annotation.OId;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.ORecordAbstract;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.core.version.ORecordVersion;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;

public class OrientDocumentTemplate implements OrientOperations {

    private final OrientDocumentDatabaseFactory dbf;

    private Set<String> defaultClusters;

    public OrientDocumentTemplate(OrientDocumentDatabaseFactory dbf) {
        this.dbf = dbf;
    }

    public ODatabaseDocumentTx database() {
        return dbf.db();
    }
    
    @Override
    public <RET> RET load(ORID recordId) {
        return database().load(recordId);
    }

    @Override
    public <RET> RET load(String recordId) {
        return load(new ORecordId(recordId));
    }

    @Override
    public <RET> RET save(Object entity) {
        return database().save((ODocument) entity);
    }

    @Override
    public <RET> RET save(Object entity, String cluster) {
        return database().save((ODocument) entity, cluster);
    }

    @Override
    public Long count(OSQLQuery<?> query, Object... values) {
        return ((ODocument) database().query(query, values).get(0)).field("count");
    }

    @Override
    public long countClass(String iClassName) {
        return database().countClass(iClassName);
    }

    @Override
    public long countClass(Class<?> iClass) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long countClusterElements(String iClusterName) {
        return database().countClusterElements(iClusterName);
    }

    @Override
    public <RET extends List<?>> RET query(OQuery<?> query, Object... values) {
        return database().query(query, values);
    }

    @Override
    public <RET> RET queryForObject(OSQLQuery<?> query, DetachMode detachMode, Object... values) {
        List<RET> list = query(query, values);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <RET extends List<?>> RET query(OQuery<?> query, DetachMode detachMode, Object... values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <RET> OObjectIteratorClass<RET> browseClass(Class<RET> iClusterClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDefaultClusterId(Class<?> domainClass) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getClusterNameById(int iClusterId) {
        return database().getClusterNameById(iClusterId);
    }

    @Override
    public int getClusterIdByName(String clusterName, Class<?> aClass) {
        OClass oClass = database().getMetadata().getSchema().getClass(aClass);
        for(int clusterId : oClass.getClusterIds()){
            if(getClusterNameById(clusterId).equals(clusterName)){
                return clusterId;
            }
        }
        
        throw new OException("Cluster " + clusterName + " not found");
    }

    @Override
    public String getClusterNameByRid(String rid) {
        return getClusterNameById(new ORecordId(rid).getClusterId());
    }

    @Override
    public List<String> getClusterNamesByClass(Class<?> entityClass, boolean includeDefault) {
        int[] clusterIds = database().getMetadata().getSchema().getClass(entityClass).getClusterIds();
        int defaultCluster = getDefaultClusterId(entityClass);

        List<String> clusters = new ArrayList<>(clusterIds.length);
        for (int clusterId : clusterIds) {
            if (includeDefault || clusterId != defaultCluster) {
                clusters.add(getClusterNameById(clusterId));
            }
        }

        return clusters;
    }

    @Override
    public boolean isDefault(String clusterName) {
        loadDefaultClusters();
        return defaultClusters.contains(clusterName);
    }
    
    private void loadDefaultClusters() {
        if (defaultClusters == null) {
            synchronized (this) {
                if (defaultClusters == null) {
                    defaultClusters = new HashSet<>();
                    for (OClass oClass : database().getMetadata().getSchema().getClasses()) {
                        String defaultCluster = getClusterNameById(oClass.getDefaultClusterId());
                        defaultClusters.add(defaultCluster);
                    }
                }
            }
        }
    }
    
    @Override
    public boolean existsClass(Class<?> clazz) {
        return existsClass(clazz.getSimpleName());
    }

    @Override
    public boolean existsClass(String className) {
        return database().getMetadata().getSchema().existsClass(className);
    }

    @Override
    public String getRid(Object entity) {
        Class<?> clazz = entity.getClass();
        while(clazz != Object.class){
            for(Field field : clazz.getDeclaredFields()){
                OId ridAnnotation = field.getAnnotation(OId.class);
                if(ridAnnotation != null){
                    field.setAccessible(true);
                    try{
                        Object rid = field.get(entity);
                        if(rid == null) {
                            Method method = clazz.getDeclaredMethod(getterName(field.getName()));
                            rid = method.invoke(entity);
                        }
                        return  rid != null ? rid.toString() : null;
                    } catch (IllegalAccessException | IllegalArgumentException
                            | NoSuchMethodException | InvocationTargetException ex){
                        throw new RuntimeException(ex);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    private String getterName(String propertyName) {
        return "get" + propertyName.substring(0, 1).toUpperCase() +
                propertyName.substring(1).toLowerCase();
    }

    @Override
    public void registerEntityClass(Class<?> domainClass) {

    }

    @Override
    public <RET> RET command(OCommandSQL command, Object... args) {
        return database().command(command).execute(args);
    }

    @Override
    public <RET> RET command(String sql, Object... args) {
        return command(new OCommandSQL(sql), args);
    }

    @Override
    public ODatabaseDocument delete(ORID iRID) {
        return database().delete(iRID);
    }

    @Override
    public ODatabaseDocument delete(Object iRecord) {
        return database().delete((ORecordAbstract) iRecord);
    }

    public ODatabase<ORecord> delete(ORID iRID, ORecordVersion iVersion) {
        return database().delete(iRID, iVersion);
    }
}
