package org.springframework.data.orient.object.person.cluster;

import java.util.List;

import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.annotation.Cluster;
import org.test.data.Person;

@Cluster("person_temp")
public interface PersonRepository extends OrientObjectRepository<Person> {

    @Cluster("person_temp2")
    List<Person> findByLastName(String lastName);
}
