package org.springframework.data.orient.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrientRepository<T> extends CrudRepository<T, String> {

}
