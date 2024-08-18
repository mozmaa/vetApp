package vetApp.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class AddressDTO {
	
	 @Positive(message = "Id must be positive.")
	 private Long id;
	 
	 @Length(min = 5, max = 50)
	 private String streetName;
	 
	 @Pattern(regexp = "^\\d+[a-zA-Z]?$|^[bb][BB]$" , message = "Building/home number must match a pattern") 
	 private String buildingNumber;
	 
	 private String appartmentNumber;
	 
	 @NotBlank
	 private String city;
	 
	 @Pattern(regexp = "^\\d{5}", message = "Must be exactly 5 digits")
	 private String zipCode;
	 
	 public AddressDTO () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getAppartmentNumber() {
		return appartmentNumber;
	}

	public void setAppartmentNumber(String appartmentNumber) {
		this.appartmentNumber = appartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	 
	 
}
