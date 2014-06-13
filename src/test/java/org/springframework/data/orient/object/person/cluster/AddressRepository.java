package org.springframework.data.orient.object.person.cluster;

import org.springframework.data.orient.repository.OrientRepository;
import org.springframework.data.orient.repository.annotation.Cluster;
import org.test.data.Address;

@Cluster("address_temps")
public interface AddressRepository extends OrientRepository<Address> {

}
