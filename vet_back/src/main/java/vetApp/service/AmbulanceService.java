package vetApp.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import vetApp.model.Ambulance;

public interface AmbulanceService {

	Optional<Ambulance> findOne(Long id);

	Ambulance save(Ambulance ambulance);

	Page<Ambulance> find(String name, String city, Boolean closed, int pageNo);

	Ambulance update(Ambulance ambulance);

	Ambulance delete(Long id);

}
