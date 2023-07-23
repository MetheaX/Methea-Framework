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
 * Date : 9/4/2019
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_permission")
public class Permission extends BaseEntity<Permission> {

    @Serial
    private static final long serialVersionUID = -1637342316864349272L;
    @Id
    @Column(name = "permission_id", length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "core_perm_allowed_method", joinColumns = @JoinColumn(name = "public_perm_id"))
    @Column(name = "http_method")
    private List<String> allowedMethod;
}
