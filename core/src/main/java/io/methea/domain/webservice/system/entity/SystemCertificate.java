package io.methea.domain.webservice.system.entity;

import io.methea.domain.baseentity.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 05/05/2020
 */
@Entity
@Table(name = "tbl_core_system_certificate")
public class SystemCertificate extends BaseEntity<SystemCertificate> {
    private static final long serialVersionUID = -3543977124810730421L;

    @Id
    private String id;
    @Type(type = "text")
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Type(type = "text")
    @Column(name = "private_key", nullable =  false)
    private String privateKey;
    @Type(type = "text")
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
