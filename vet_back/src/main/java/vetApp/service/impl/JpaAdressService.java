package vetApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vetApp.model.Address;
import vetApp.repository.AddressRepository;
import vetApp.service.AddressService;

@Service
public class JpaAdressService implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Optional<Address> findOne(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public void save(Address address) {
		addressRepository.save(address)	;
	}

}
