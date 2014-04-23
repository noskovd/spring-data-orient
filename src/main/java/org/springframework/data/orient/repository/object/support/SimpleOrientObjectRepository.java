package org.springframework.data.orient.repository.object.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.orient.repository.object.OrientObjectRepository;
import org.springframework.orm.orient.OrientObjectTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orientechnologies.orient.core.id.ORecordId;

/**
 * Default implementation of the {@link org.springframework.data.repository.CrudRepository} interface for OrientDB.
 * 
 * @author Dzmitry_Naskou
 * @param <T> the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 */
@Repository
@Transactional(readOnly = true)
public class SimpleOrientObjectRepository<T> implements OrientObjectRepository<T> {

	private final OrientObjectTemplate template;
	
	private final Class<T> domainClass;

	public SimpleOrientObjectRepository(OrientObjectTemplate template, Class<T> domainClass) {
		super();
		this.template = template;
		this.domainClass = domainClass;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(readOnly = false)
	public <S extends T> S save(S entity) {
		return template.save(entity);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Iterable)
	 */
	@Transactional(readOnly = false)
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        List<S> result = new ArrayList<S>();

        for (S entity : entities) {
            result.add(save(entity));
        }

        return result;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
	 */
	public T findOne(String id) {
		return template.load(new ORecordId(id));
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
	 */
	public boolean exists(String id) {
		return findOne(id) != null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public Iterable<T> findAll() {
		return template.browseClass(domainClass);
	}

	public Iterable<T> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	public long count() {
		return template.countClass(domainClass);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		template.delete(new ORecordId(id));
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		template.delete(entity);
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
	 */
	@Transactional(readOnly = false)
	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Transactional(readOnly = false)
	public void deleteAll() {
		for (T entity : template.browseClass(domainClass)) {
            template.delete(entity);
        }
	}

    @Override
    public Class<T> getDomainClass() {
        return domainClass;
    }
}
