package org.springframework.data.orient.object.repository.support;

import org.springframework.data.repository.core.support.AbstractEntityInformation;

public class OrientMetamodelEntityInformation<T> extends AbstractEntityInformation<T, String> {

	public OrientMetamodelEntityInformation(Class<T> domainClass) {
		super(domainClass);
	}

	public String getId(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<String> getIdType() {
		return String.class;
	}
}
