package io.github.metheax.validator;

import io.github.metheax.domain.entity.TResource;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import io.github.metheax.domain.entity.TRole;
import io.github.metheax.domain.binder.RoleURIBinder;
import io.github.metheax.repository.RoleRepository;
import io.github.metheax.repository.ResourceRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@Component
public class RoleURIValidator extends AbstractMetheaValidator<RoleURIBinder> {

    private final RoleRepository roleRepository;
    private final ResourceRepository uriRepository;

    public RoleURIValidator(RoleRepository roleRepository, ResourceRepository uriRepository) {
        this.roleRepository = roleRepository;
        this.uriRepository = uriRepository;
    }

    @Override
    public void validate(RoleURIBinder binder, Map<String, String> errors) {
        Optional<TRole> role = roleRepository.findById(binder.getRoleId());
        if (!role.isPresent()) {
            errors.put("roleId", "Error: Provided role invalid!!!");
        }
        Optional<TResource> uri = uriRepository.findById(binder.getUriId());
        if (!uri.isPresent()) {
            errors.put("uriId", "Error: Provided URI invalid!!!");
        } else {
            //binder.setUriName(uri.get().getUriName());
        }
    }
}
