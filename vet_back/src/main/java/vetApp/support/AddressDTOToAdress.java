package vetApp.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vetApp.model.Address;
import vetApp.service.AddressService;
import vetApp.web.dto.AddressDTO;

@Component
public class AddressDTOToAdress implements Converter<AddressDTO, Address> {
	
	@Autowired
	private AddressService adressService;

	/**
     * Converts an AddressDTO to an Address entity.
     * 
     * @param addressDTO the AddressDTO to convert
     * @return the corresponding Address entity
     */
	@Override
	public Address convert(AddressDTO addressDTO) {
		Address entity = null;

		// If the ID is null, create a new Address instance. Therefore we know we are creating a new Address
        if(addressDTO.getId() == null) {
            entity = new Address();
        }else {
        	// Find the existing Address entity by ID. If we are here we are doing update of existing Address
            Optional<Address> adressOptional = adressService.findOne(addressDTO.getId());
            if(adressOptional.isPresent()){
                entity = adressOptional.get();
            }
        }

        // If an Address entity was found or created, set its properties
        if(entity != null) {
            entity.setStreetName(addressDTO.getStreetName());
            entity.setBuildingNumber(addressDTO.getBuildingNumber());
            entity.setAppartmentNumber(addressDTO.getAppartmentNumber());
            entity.setCity(addressDTO.getCity());
            entity.setZipCode(addressDTO.getZipCode());
        }

        return entity;
	}

}
