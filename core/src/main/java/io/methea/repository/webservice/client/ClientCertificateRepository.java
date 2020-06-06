package io.methea.repository.webservice.client;

import io.methea.domain.webservice.client.entity.ClientCertificate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientCertificateRepository extends CrudRepository<ClientCertificate, String> {
    ClientCertificate findClientCertificateByClientId(String clientId);

    @Modifying
    @Query("DELETE FROM ClientCertificate o WHERE o.clientId = ?1")
    void revokeClientCertificate(String clientId);
}
