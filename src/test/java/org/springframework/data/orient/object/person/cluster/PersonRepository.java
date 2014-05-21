package org.springframework.data.orient.object.person.cluster;

import java.util.List;

import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.test.data.Person;

public interface PersonRepository extends OrientObjectRepository<Person> {

    List<Person> findByLastName(String lastName);
}
