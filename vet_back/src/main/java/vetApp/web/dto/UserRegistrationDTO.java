package vetApp.web.dto;

import javax.validation.constraints.NotBlank;

public class UserRegistrationDTO extends UserDTO{

    @NotBlank(message = "Password missing.")
    private String newPassword;

    @NotBlank(message = "Repeated password missing.")
    private String repeatedPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

}
