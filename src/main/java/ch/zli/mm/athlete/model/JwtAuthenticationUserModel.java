package ch.zli.mm.athlete.model;

/**
 * @version 10.12.2020
 * votingtool - AuthenticationUserModel
 * <p>
 * Responsible for temporarily managing user credentials (=> secure against XSS)
 */

public class JwtAuthenticationUserModel {
    private String email;
    private String password;

    public JwtAuthenticationUserModel(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}