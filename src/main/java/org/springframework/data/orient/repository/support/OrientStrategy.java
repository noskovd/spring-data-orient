package org.springframework.data.orient.repository.support;


public interface OrientStrategy<T> {

    <S extends T> S save(S entity);
    
    long count();
    
    String getSource();
}
