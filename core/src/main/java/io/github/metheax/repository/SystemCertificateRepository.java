package io.github.metheax.repository;

import io.github.metheax.domain.entity.TSystemCertificate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemCertificateRepository extends CrudRepository<TSystemCertificate, String> {
    TSystemCertificate findSystemCertificateByCode(String code);

    List<TSystemCertificate> findSystemCertificateByCodeIn(List<String> codes);

    TSystemCertificate findSystemCertificateByCodeAndStatus(String code, String status);
}
