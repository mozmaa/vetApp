package vetApp.web.dto;

import javax.validation.constraints.NotBlank;

public class UserChangePassword {

    @NotBlank(message = "User name missing.")
    private String userName;

    @NotBlank(message = "Old password missing.")
    private String oldPassword;

    @NotBlank(message = "New passowrd missing.")
    private String newPassword;

    @NotBlank(message = "Repeated password missing.")
    private String repeatedPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

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
