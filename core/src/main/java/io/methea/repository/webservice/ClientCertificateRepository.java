package io.methea.repository.webservice;

import io.methea.domain.webservice.ClientCertificate;
import org.springframework.data.repository.CrudRepository;

public interface ClientCertificateRepository extends CrudRepository<ClientCertificate, String> {
    ClientCertificate findClientCertificateByClientId(String clientId);
}
