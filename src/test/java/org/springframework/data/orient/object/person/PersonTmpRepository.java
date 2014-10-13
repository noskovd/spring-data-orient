package org.springframework.data.orient.object.person;

import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.annotation.Source;
import org.test.data.Person;

@Source("person_tmp")
public interface PersonTmpRepository extends OrientObjectRepository<Person> {

}
