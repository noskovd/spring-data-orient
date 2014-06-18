package org.springframework.data.orient.object.person.cluster;

import org.springframework.data.orient.repository.OrientRepository;
import org.springframework.data.orient.repository.annotation.Source;
import org.test.data.Address;

@Source("address_temps")
public interface AddressClusteredRepository extends OrientRepository<Address> {

}
