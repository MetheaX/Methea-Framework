package io.github.metheax.domain.entity;

import io.github.metheax.constant.MetheaConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_user")
public class User extends BaseEntity<User> {

    @Serial
    private static final long serialVersionUID = 6855359535244575164L;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "core_user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Collection<Role> roles = new ArrayList<>();

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

    public String getPassword() {
        return MetheaConstant.ARGON_PREFIX.concat(password);
    }

    public void setPassword(String password) {
        this.password = new Argon2PasswordEncoder(MetheaConstant.SALT_LENGTH, MetheaConstant.HASH_LENGTH, MetheaConstant.PARALLELISM,
                MetheaConstant.MEMORY, MetheaConstant.ITERATIONS).encode(password).split(MetheaConstant.ARGON_PREFIX_SPLIT)[1];
    }
}
