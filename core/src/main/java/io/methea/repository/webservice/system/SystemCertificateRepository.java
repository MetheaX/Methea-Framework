package io.methea.repository.webservice.system;

import io.methea.domain.webservice.system.entity.SystemCertificate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemCertificateRepository extends CrudRepository<SystemCertificate, String> {
    SystemCertificate findSystemCertificateByCode(String code);

    List<SystemCertificate> findSystemCertificateByCodeIn(List<String> codes);

    SystemCertificate findSystemCertificateByCodeAndStatus(String code, String status);
}
