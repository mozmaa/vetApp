package vetApp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vetApp.model.Address;
import vetApp.web.dto.AddressDTO;

@Component
public class AddressToAdressDTO implements Converter<Address, AddressDTO> {

	@Override
	public AddressDTO convert(Address address) {
		AddressDTO adressDTO = new AddressDTO();
		
		adressDTO.setId(address.getId());
		adressDTO.setStreetName(address.getStreetName());
		adressDTO.setBuildingNumber(address.getBuildingNumber());
		adressDTO.setAppartmentNumber(address.getAppartmentNumber());
		adressDTO.setCity(address.getCity());
		adressDTO.setZipCode(address.getZipCode());
		
		return adressDTO;
	}
	
	public List<AddressDTO> convert(List<Address> addresses){
        List<AddressDTO> addressDTOS = new ArrayList<>();

        for(Address a : addresses) {
            AddressDTO dto = convert(a);
            addressDTOS.add(dto);
        }

        return addressDTOS;
    }

}
