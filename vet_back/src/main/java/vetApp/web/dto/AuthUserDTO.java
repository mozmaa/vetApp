package vetApp.web.dto;

import javax.validation.constraints.NotBlank;

public class AuthUserDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public AuthUserDTO() {}

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
