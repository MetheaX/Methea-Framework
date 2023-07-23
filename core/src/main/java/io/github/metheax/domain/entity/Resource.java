package io.github.metheax.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_resource")
public class Resource extends BaseEntity<Resource> {

    @Serial
    private static final long serialVersionUID = -8160909806511195577L;
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "resource_name", nullable = false)
    private String resourceName;
}
