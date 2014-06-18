package org.springframework.data.orient.object.person.cluster;

import java.util.List;

import org.springframework.data.orient.object.repository.OrientObjectRepository;
import org.springframework.data.orient.repository.OrientCluster;
import org.test.data.Person;

public interface PersonClusteredRepository extends OrientObjectRepository<Person> {

    List<Person> findByLastName(String lastName);
    
    List<Person> findByFirstName(String firstName, OrientCluster cluster);
}
