package io.methea.validator.configuration.uri;

import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.uri.dto.RoleURIBinder;
import io.methea.domain.configuration.uri.entity.TMstURI;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.uri.URIRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@Component
public class RoleURIValidator extends AbstractMetheaValidator<RoleURIBinder> {

    private final UserRoleRepository roleRepository;
    private final URIRepository uriRepository;

    public RoleURIValidator(UserRoleRepository roleRepository, URIRepository uriRepository) {
        this.roleRepository = roleRepository;
        this.uriRepository = uriRepository;
    }

    @Override
    public void validate(RoleURIBinder binder, Map<String, String> errors) {
        Optional<TRole> role = roleRepository.findById(binder.getRoleId());
        if (!role.isPresent()) {
            errors.put("roleId", "Error: Provided role invalid!!!");
        }
        Optional<TMstURI> uri = uriRepository.findById(binder.getUriId());
        if (!uri.isPresent()) {
            errors.put("uriId", "Error: Provided URI invalid!!!");
        } else {
            binder.setUriName(uri.get().getUriName());
        }
    }
}
