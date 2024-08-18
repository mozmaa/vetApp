package vetApp.service;

import java.util.Optional;

import vetApp.model.Address;

public interface AddressService {

	Optional<Address> findOne(Long id);

	void save(Address address);

}
