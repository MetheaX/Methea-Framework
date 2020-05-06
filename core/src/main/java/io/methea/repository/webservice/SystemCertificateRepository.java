package io.methea.repository.webservice;

import io.methea.domain.webservice.SystemCertificate;
import org.springframework.data.repository.CrudRepository;

public interface SystemCertificateRepository extends CrudRepository<SystemCertificate, String> {
    SystemCertificate findSystemCertificateByCode(String code);
}
