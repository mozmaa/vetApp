package vetApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vetApp.model.Address;
import vetApp.model.Ambulance;
import vetApp.repository.AmbulanceRepository;
import vetApp.service.AddressService;
import vetApp.service.AmbulanceService;

@Service
public class JpaAmbulanceService implements AmbulanceService {
	
	@Autowired
	private AmbulanceRepository ambulanceRepository;
	
	@Autowired
	private AddressService addressService;
	
	
	/**
     * Finds an Ambulance by its ID.
     * 
     * @param id the ID of the Ambulance.
     * @return an Optional containing the found Ambulance or an empty Optional if not found.
     */
	@Override
	public Optional<Ambulance> findOne(Long id) {
		return ambulanceRepository.findById(id);
	}

	/**
     * Saves a new or existing Ambulance. Also saves the associated Address.
     * 
     * @param ambulance the Ambulance to save.
     * @Transactional prevents partial updates/save if case of exceptions
     * @return the saved Ambulance.
     */
	@Override
	@Transactional
	public Ambulance save(Ambulance ambulance) {
		addressService.save(ambulance.getAddress());
		return ambulanceRepository.save(ambulance);
	}

	/**
     * Searches for Ambulances based on provided criteria and returns a paginated result.
     * 
     * @param name the name to search for (optional).
     * @param city the city to search for (optional, within the Address).
     * @param closed the closed status to filter by (optional).
     * @param pageNo the page number for pagination.
     * @return a Page of Ambulances matching the search criteria.
     */
	@Override
	public Page<Ambulance> find(String name, String city, Boolean closed, int pageNo) {
		return ambulanceRepository.search(name, city, closed, PageRequest.of(pageNo, 10));
	}

	/**
     * Updates an existing Ambulance. If the Address has changed, updates the Address as well.
     * 
     * @param ambulance the Ambulance to update.
     * @Transactional prevents partial updates/save if case of exceptions
     * @return the updated Ambulance.
     */
	@Override
	@Transactional
	public Ambulance update(Ambulance ambulance) {
		Optional<Address> optAddress = Optional.ofNullable(addressService.findOne(ambulance.getAddress().getId()).orElse(null));
		Address address = optAddress.get();
		if(!address.equals(ambulance.getAddress())) {
			addressService.save(ambulance.getAddress());
		}
		return ambulanceRepository.save(ambulance);
	}

	/**
     * Marks an Ambulance as closed instead of deleting it.
     * 
     * @param id the ID of the Ambulance to close.
     * @return the updated Ambulance with closed status.
     */
	@Override
	public Ambulance delete(Long id) {
		Optional<Ambulance> optAmbulance = Optional.ofNullable(ambulanceRepository.findById(id).orElse(null));
		Ambulance ambulance = optAmbulance.get();
		// Mark the Ambulance as closed making it logically deleted but keeping data.
		if(ambulance != null) {
			ambulance.setClosed(true);
			ambulanceRepository.save(ambulance);
		}
		return ambulance;
	}

}
