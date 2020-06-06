package io.methea.repository.webservice.system;

import io.methea.domain.webservice.system.entity.SystemCertificate;
import org.springframework.data.repository.CrudRepository;

public interface SystemCertificateRepository extends CrudRepository<SystemCertificate, String> {
    SystemCertificate findSystemCertificateByCode(String code);
    SystemCertificate findSystemCertificateByCodeAndStatus(String code, String status);
}
