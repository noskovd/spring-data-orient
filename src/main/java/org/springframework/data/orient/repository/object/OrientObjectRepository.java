package org.springframework.data.orient.repository.object;

import org.springframework.data.orient.repository.OrientRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * The specific extension for {@link OObjectDatabaseTx} database.
 *
 * @author Dzmitry_Naskou
 * @param <T> the generic type
 */
@NoRepositoryBean
public interface OrientObjectRepository<T> extends OrientRepository<T> {

}
