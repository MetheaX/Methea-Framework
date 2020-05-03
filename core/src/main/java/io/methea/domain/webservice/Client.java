package io.methea.domain.webservice;

import io.methea.domain.baseentity.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
@Entity
@Table(name = "tbl_core_client")
public class Client extends BaseEntity<Client> {
    private static final long serialVersionUID = -5611817251891629942L;

    @Id
    private String id;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_secret", nullable = false, length = 1024)
    private String clientSecret;

    @Transient
    private String oneTimeDisplaySecretKey;

    @Type(type = "text")
    @Column(name = "verify_code", nullable = false)
    private String verifyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOneTimeDisplaySecretKey() {
        return oneTimeDisplaySecretKey;
    }

    public void setOneTimeDisplaySecretKey(String oneTimeDisplaySecretKey) {
        this.oneTimeDisplaySecretKey = oneTimeDisplaySecretKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
