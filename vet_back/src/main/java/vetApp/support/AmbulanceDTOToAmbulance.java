package vetApp.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vetApp.model.Ambulance;
import vetApp.service.AmbulanceService;
import vetApp.web.dto.AmbulanceDTO;

@Component
public class AmbulanceDTOToAmbulance implements Converter<AmbulanceDTO, Ambulance> {
	
	@Autowired
	private AmbulanceService ambulanceService;
	
	@Autowired
	private AddressDTOToAdress toAddress;

	@Override
	public Ambulance convert(AmbulanceDTO ambulanceDTO) {
		Ambulance entity = null;

        if(ambulanceDTO.getId() == null) {
            entity = new Ambulance();
        }else {
            Optional<Ambulance> ambulanceOptional = ambulanceService.findOne(ambulanceDTO.getId());
            if(ambulanceOptional.isPresent()){
                entity = ambulanceOptional.get();
            }
        }

        if(entity != null) {
        	entity.setName(ambulanceDTO.getName());
            entity.setPhoneNumber(ambulanceDTO.getPhoneNumber());
            entity.setMobilePhoneNumber(ambulanceDTO.getMobilePhoneNumber());
            entity.setAddress(toAddress.convert(ambulanceDTO.getAddressDTO()));
            entity.setFacebookLink(ambulanceDTO.getFacebookLink());
            entity.setInstagramLink(ambulanceDTO.getInstagramLink());
            entity.setClosed(ambulanceDTO.getClosed());
        }

        return entity;
	}


}
