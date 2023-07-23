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
 * Date : 12/09/2020
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_public_permission")
public class PublicPermission extends BaseEntity<PublicPermission> {

    @Serial
    private static final long serialVersionUID = 8696065132921614456L;

    @Id
    @Column(name = "public_perm_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "core_perm_allowed_method", joinColumns = @JoinColumn(name = "public_perm_id"))
    @Column(name = "http_method")
    private List<String> allowedMethod;
}
