package org.springframework.data.orient.repository.object;

import org.springframework.data.orient.repository.OrientRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrientObjectRepository<T> extends OrientRepository<T> {

}
