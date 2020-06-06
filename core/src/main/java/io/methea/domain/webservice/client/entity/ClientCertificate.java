package io.methea.domain.webservice.client.entity;

import io.methea.domain.baseentity.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
@Entity
@Table(name = "tbl_core_client_certificate")
public class ClientCertificate extends BaseEntity<ClientCertificate> {
    private static final long serialVersionUID = 5357913874216512890L;

    @Id
    private String id;
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Type(type = "text")
    @Column(name = "verify_key", nullable = false)
    private String verifyKey;

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

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }
}
