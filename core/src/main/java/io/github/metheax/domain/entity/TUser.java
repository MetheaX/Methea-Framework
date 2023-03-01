package io.github.metheax.domain.entity;

import io.github.metheax.constant.MetheaConstant;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
@Entity
@Table(name = "core_user")
public class TUser extends BaseEntity<TUser> {

    private static final long serialVersionUID = 6855359535244575164L;
    @Id
    @Column(name = "user_id", length = 36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private TGroup group;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "core_user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Collection<TRole> roles = new ArrayList<>();

    @Column(name = "username", unique = true, nullable = false, length = 36)
    private String username;
    @Column(name = "identity_code", unique = true, nullable = false, length = 36)
    private String identityCode;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "first_name_oth")
    private String firstNameOth;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "last_name_oth")
    private String lastNameOth;
    @Column(name = "phone", nullable = false, length = 36)
    private String phone;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 512)
    private String password;
    @Column(name = "frc_usr_rst_pwd", nullable = false)
    private String forceUserResetPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public TGroup getGroup() {
        return group;
    }

    public void setGroup(TGroup group) {
        this.group = group;
    }

    public Collection<TRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<TRole> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameOth() {
        return firstNameOth;
    }

    public void setFirstNameOth(String firstNameOth) {
        this.firstNameOth = firstNameOth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameOth() {
        return lastNameOth;
    }

    public void setLastNameOth(String lastNameOth) {
        this.lastNameOth = lastNameOth;
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

    public String getPassword() {
        return MetheaConstant.ARGON_PREFIX.concat(password);
    }

    public void setPassword(String password) {
        this.password = new Argon2PasswordEncoder(MetheaConstant.SALT_LENGTH, MetheaConstant.HASH_LENGTH, MetheaConstant.PARALLELISM,
                MetheaConstant.MEMORY, MetheaConstant.ITERATIONS).encode(password).split(MetheaConstant.ARGON_PREFIX_SPLIT)[1];
    }
}
