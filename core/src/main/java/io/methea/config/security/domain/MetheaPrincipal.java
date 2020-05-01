package io.methea.config.security.domain;

/**
 * Author : DKSilverX
 * Date : 30/04/2020
 */
public class MetheaPrincipal {

    private String username;
    private String phone;
    private String email;
    private String forceUserResetPassword;

    public MetheaPrincipal() {
    }

    public MetheaPrincipal(String username, String phone, String email, String forceUserResetPassword) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.forceUserResetPassword = forceUserResetPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForceUserResetPassword() {
        return forceUserResetPassword;
    }

    public void setForceUserResetPassword(String forceUserResetPassword) {
        this.forceUserResetPassword = forceUserResetPassword;
    }
}
