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
 * Date : 21/08/2019
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_group")
public class Group extends BaseEntity<Group> {
    @Serial
    private static final long serialVersionUID = -4855334223690873520L;
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "group_code", nullable = false, unique = true, length = 18)
    private String groupCode;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @Column(name = "group_name_oth")
    private String groupNameOth;
    @Column(name = "remarks")
    private String remarks;
}
