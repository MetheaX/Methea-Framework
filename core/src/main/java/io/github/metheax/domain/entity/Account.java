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
@Table(name = "core_account")
public class Account extends BaseEntity<Account> {

    @Serial
    private static final long serialVersionUID = -8120388696917673449L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_code", nullable = false, unique = true, length = 18)
    private String accountCode;
    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "account_name_oth")
    private String accountNameOth;
    @Column(name = "account_email", nullable =  false)
    private String accountEmail;
    @Column(name = "account_address")
    private String accountAddress;
}
