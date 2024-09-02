package vetApp.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vetApp.model.Ambulance;
import vetApp.service.AmbulanceService;
import vetApp.support.AmbulanceDTOToAmbulance;
import vetApp.support.AmbulanceToAmbulanceDTO;
import vetApp.web.dto.AmbulanceDTO;

@RestController
@RequestMapping(value = "/api/ambulances", produces = MediaType.APPLICATION_JSON_VALUE)
public class AmbulanceController {
	
	@Autowired
	private AmbulanceService ambulanceService;
	
	@Autowired
	private AmbulanceDTOToAmbulance toAmbulance;
	
	@Autowired
	private AmbulanceToAmbulanceDTO toDTO;
	
	/**
     * Retrieves a paginated list of AmbulanceDTOs, optionally filtered by name, city, and closed status.
     * 
     * @preAuthorize defines who can access 
     * @param name the name of the ambulance to filter by (optional).
     * @param city the city of the ambulance to filter by (optional).
     * @param closed the closed status of the ambulance to filter by (default is false).
     * @param pageNo the page number for pagination (default is 0).
     * @return a ResponseEntity containing a list of AmbulanceDTOs and pagination headers.
     */
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
    public ResponseEntity<List<AmbulanceDTO>> getAll(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String city,
            @RequestParam(defaultValue = "false") Boolean closed,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

		// Retrieve a paginated list of Ambulance entities from the service
        Page<Ambulance> page = ambulanceService.find(name, city, closed, pageNo);

        // Add pagination information to the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        // Convert the page of Ambulance entities to DTOs and return the response
        return new ResponseEntity<>(toDTO.convert(page.getContent()),headers, HttpStatus.OK);
    }
	
	 /**
     * Updates an existing Ambulance entity based on the provided AmbulanceDTO.
     * 
     * @preAuthorize defines who can access 
     * @param id the ID of the ambulance to update.
     * @param ambulanceDTO the AmbulanceDTO containing updated information.
     * @return a ResponseEntity containing the updated AmbulanceDTO.
     */
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AmbulanceDTO> update(@PathVariable Long id, @Valid @RequestBody AmbulanceDTO ambulanceDTO){

    	 // Check if the provided ID matches the ID in the DTO
        if(!id.equals(ambulanceDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Convert the DTO to an Ambulance entity using the helper converter we made
        Ambulance ambulance = toAmbulance.convert(ambulanceDTO);

        // Update and return the updated AmbulanceDTO
        return new ResponseEntity<>(toDTO.convert(ambulanceService.update(ambulance)),HttpStatus.OK);
    }

	
	/**
     * Deletes an Ambulance entity by its ID.
     * 
     * @preAuthorize defines who can access 
     * @param id the ID of the ambulance to delete.
     * @return a ResponseEntity indicating the result of the deletion.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	
    	// Attempt to do logical deleting of the ambulance and retrieve the result
    	Ambulance ambulance = ambulanceService.delete(id);

    	// Return NO_CONTENT if the ambulance.isClosed is true, otherwise return NOT_FOUND
        if(ambulance.isClosed() == true) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    /**
     * Creates a new Ambulance entity based on the provided AmbulanceDTO.
     *
     * @preAuthorize defines who can access 
     * @param dto the AmbulanceDTO containing information for the new ambulance.
     * @return a ResponseEntity containing the created AmbulanceDTO.
     */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AmbulanceDTO> create(@RequestBody @Validated AmbulanceDTO dto){

		// Check if the DTO has an ID (which should not be the case for creation)
        if(dto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Convert the DTO to an Ambulance entity
        Ambulance ambulance = toAmbulance.convert(dto);

        // Save and return saved AmbulanceDTO
        return new ResponseEntity<>(toDTO.convert(ambulanceService.save(ambulance)), HttpStatus.CREATED);
    }
    
    
}
