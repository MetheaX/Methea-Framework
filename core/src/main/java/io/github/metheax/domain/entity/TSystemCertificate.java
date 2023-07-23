package io.github.metheax.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

/**
 * Author : Kuylim Tith
 * Date : 05/05/2020
 */
@Entity
@Table(name = "tbl_core_system_certificate")
public class TSystemCertificate extends BaseEntity<TSystemCertificate> {
    private static final long serialVersionUID = -3543977124810730421L;

    @Id
    private String id;
    @JdbcTypeCode(Types.LONGVARCHAR)
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @JdbcTypeCode(Types.LONGVARCHAR)
    @Column(name = "private_key", nullable =  false)
    private String privateKey;
    @JdbcTypeCode(Types.LONGVARCHAR)
    @Column(name = "public_key", nullable = false)
    private String publicKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
