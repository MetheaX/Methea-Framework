package io.methea.validator.configuration.permission;

import io.methea.domain.configuration.permission.dto.WhiteURIPermissionBinder;
import io.methea.domain.configuration.resource.entity.TResource;
import io.methea.repository.configuration.uri.URIRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
@Component
public class WhiteURIPermissionValidator extends AbstractMetheaValidator<WhiteURIPermissionBinder> {

    private final URIRepository repository;

    public WhiteURIPermissionValidator(URIRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(WhiteURIPermissionBinder binder, Map<String, String> errors) {
        Optional<TResource> optional = repository.findById(binder.getUriId());
        if (!optional.isPresent()) {
            errors.put("uriId", "Error: Provided uri invalid!!!");
        } else {
           // binder.setUriName(optional.get().getUriName());
        }
    }
}
