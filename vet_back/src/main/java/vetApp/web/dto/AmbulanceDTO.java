package vetApp.web.dto;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class AmbulanceDTO {

	@Positive(message = "Id must be positive.")
	private Long id;

	@NotBlank(message = "Name can't be empty")
	@Length(min = 5, max = 50, message = "Name must have atleast 5 characters")
	private String name;

	@Pattern(regexp = "^\\+381 \\d{2,3}/\\d{3}-\\d{3,4}$", message = "Invalid phone number")
	private String phoneNumber;

	@Pattern(regexp = "^\\+381 6\\d{1}/\\d{3}-\\d{3,4}$", message = "Invalid mobile phone number")
	private String mobilePhoneNumber;
	
	private Map<String, String> links;

//	private String facebookLink;
//
//	private String instagramLink;

	private Boolean closed;

	@Valid
	private AddressDTO addressDTO;

	private Set<EmployeeDTO> employeesDTO = new HashSet<>();

	private Set<ProductDTO> productsDTO = new HashSet<>();

	private Set<PetDTO> petsDTO = new HashSet<>();

	public AmbulanceDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	
	

//	public String getFacebookLink() {
//		return facebookLink;
//	}
//
//	public void setFacebookLink(String facebookLink) {
//		this.facebookLink = facebookLink;
//	}
//
//	public String getInstagramLink() {
//		return instagramLink;
//	}
//
//	public void setInstagramLink(String instagramLink) {
//		this.instagramLink = instagramLink;
//	}

	public Map<String, String> getLinks() {
		return links;
	}

	public void setLinks(Map<String, String> links) {
		this.links = links;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Set<EmployeeDTO> getEmployeesDTO() {
		return employeesDTO;
	}

	public void setEmployeesDTO(Set<EmployeeDTO> employeesDTO) {
		this.employeesDTO = employeesDTO;
	}

	public Set<ProductDTO> getProductsDTO() {
		return productsDTO;
	}

	public void setProductsDTO(Set<ProductDTO> productsDTO) {
		this.productsDTO = productsDTO;
	}

	public Set<PetDTO> getPetsDTO() {
		return petsDTO;
	}

	public void setPetsDTO(Set<PetDTO> petsDTO) {
		this.petsDTO = petsDTO;
	}

}
