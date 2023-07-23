package io.github.metheax.config.jpa;

import io.github.metheax.utils.PrincipalUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Author : Kuylim Tith
 * Date : 05/05/2020
 */
public class SecurityAuditorAware implements AuditorAware<String> {

    @Inject
    private HttpServletRequest request;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(PrincipalUtils.getUserLoginId(request));
    }
}
