package vetApp.web.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import vetApp.enumeration.Roles;

public class UserDTO {

    @Positive(message = "Id must be positive.")
    private Long id;

    @NotBlank
    private String userName;

    @NotEmpty
    @Email
    private String eMail;

    @Size(min=3, max=50)
    private String firstName;

    @Size(min=3, max=50)
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    private Roles role;
    
    private List<PetDTO> petDTOs = new ArrayList<>();
    
    private AddressDTO adressDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public List<PetDTO> getPetDTOs() {
		return petDTOs;
	}

	public void setPetDTOs(List<PetDTO> petDTOs) {
		this.petDTOs = petDTOs;
	}

	public AddressDTO getAdressDTO() {
		return adressDTO;
	}

	public void setAdressDTO(AddressDTO adressDTO) {
		this.adressDTO = adressDTO;
	}
	
	

}
