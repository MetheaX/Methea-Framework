package io.methea.validator;

import io.methea.domain.binder.WhiteURIPermissionBinder;
import io.methea.domain.entity.TResource;
import io.methea.repository.ResourceRepository;
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

    private final ResourceRepository repository;

    public WhiteURIPermissionValidator(ResourceRepository repository) {
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
