package org.springframework.data.orient.object.person;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.orient.repository.FetchPlan;
import org.springframework.data.orient.repository.object.Detach;
import org.springframework.data.orient.repository.object.DetachMode;
import org.springframework.data.orient.repository.object.OrientObjectRepository;
import org.springframework.data.orient.repository.object.Query;

public interface PersonRepository extends OrientObjectRepository<Person> {

    @Query("select from person where firstName = ?")
    List<Person> findByFirstName(String firstName);
    
    Page<Person> findByFirstName(String firstName, Pageable pageable);
    
    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameLike(String lastName);

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findByFirstNameOrLastName(String firstName, String lastName);

    List<Person> findByFirstNameLike(String string);
    
    List<Person> findByFirstNameStartsWith(String firstName);

    Long countByFirstName(String firstName);

    @Detach(DetachMode.ENTITY)
    List<Person> findByAddress_City(String city);

    @FetchPlan("*:-1")
    List<Person> findByAddress_Country(String city);
}
