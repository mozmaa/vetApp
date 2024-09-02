package vetApp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vetApp.model.Ambulance;
import vetApp.web.dto.AmbulanceDTO;

@Component
public class AmbulanceToAmbulanceDTO implements Converter<Ambulance, AmbulanceDTO> {

	@Autowired
	private AddressToAdressDTO toAddressDTO;

	@Override
	public AmbulanceDTO convert(Ambulance ambulance) {
		AmbulanceDTO ambulanceDTO = new AmbulanceDTO();

		ambulanceDTO.setId(ambulance.getId());
		ambulanceDTO.setName(ambulance.getName());
		ambulanceDTO.setPhoneNumber(ambulance.getPhoneNumber());
		ambulanceDTO.setMobilePhoneNumber(ambulance.getMobilePhoneNumber());
		ambulanceDTO.setClosed(ambulance.isClosed());
		ambulanceDTO.setAddressDTO(toAddressDTO.convert(ambulance.getAddress()));
		ambulanceDTO.setLinks(ambulance.getLinks());
		if (!ambulance.getEmployees().isEmpty()) {
			// convert empolyyes
		}
		if (!ambulance.getPets().isEmpty()) {
			// convert pets
		}
		if (!ambulance.getProducts().isEmpty()) {
			// convert products
		}

		return ambulanceDTO;
	}

	public List<AmbulanceDTO> convert(List<Ambulance> ambulances) {
		List<AmbulanceDTO> ambulanceDTOS = new ArrayList<>();

		for (Ambulance a : ambulances) {
			AmbulanceDTO dto = convert(a);
			ambulanceDTOS.add(dto);
		}

		return ambulanceDTOS;
	}

}
