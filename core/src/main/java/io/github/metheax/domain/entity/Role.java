package io.github.metheax.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;
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
@Table(name = "core_role")
public class Role extends BaseEntity<Role> {
    @Serial
    private static final long serialVersionUID = 707732334293126239L;
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "role_code", nullable = false, unique = true, length = 18)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "name_oth")
    private String nameOth;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permission> permissions;
}
