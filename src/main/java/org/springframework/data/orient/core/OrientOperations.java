package org.springframework.data.orient.core;

import java.util.List;

import org.springframework.data.orient.object.repository.DetachMode;

import com.orientechnologies.orient.core.db.ODatabaseComplex;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.record.ORecordInternal;
import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;

public interface OrientOperations {

    <RET> RET load(ORID iRecordId);
    
    <RET> RET save(Object entity);
    
    <RET> RET save(Object iPojo, String iClusterName);
    
    Long count(OSQLQuery<?> query, Object... values);
    
    long countClass(String iClassName);
    
    long countClass(Class<?> iClass);
    
    long countClusterElements(String iClusterName);
    
    <RET extends List<?>> RET query(OQuery<?> query, Object... values);
    
    <RET> RET queryForObject(OSQLQuery<?> query, DetachMode detachMode, Object... values);
    
    <RET extends List<?>> RET query(OQuery<?> query, DetachMode detachMode, Object... values);
    
    ODatabaseComplex<Object> delete(ORecordInternal<?> iRecord);
    
    ODatabaseObject delete(ORID iRID);
    
    ODatabaseObject delete(Object iPojo);
    
    <RET> OObjectIteratorClass<RET> browseClass(Class<RET> iClusterClass);
    
    int getDefaultClusterId(Class<?> domainClass);
    
    String getClusterNameById(int iClusterId);
    
    String getClusterNameByRid(String rid);
}
