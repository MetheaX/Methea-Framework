package io.github.metheax.repository;

import io.github.metheax.domain.entity.TSystemCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemCertificateRepository extends JpaRepository<TSystemCertificate, String> {
    TSystemCertificate findSystemCertificateByCode(String code);

    List<TSystemCertificate> findSystemCertificateByCodeIn(List<String> codes);

    TSystemCertificate findSystemCertificateByCodeAndStatus(String code, String status);
}
