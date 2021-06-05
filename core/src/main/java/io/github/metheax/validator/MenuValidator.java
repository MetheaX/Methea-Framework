package io.github.metheax.validator;

import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.entity.TResource;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.domain.binder.MenuBinder;
import io.github.metheax.repository.GroupRepository;
import io.github.metheax.repository.MenuRepository;
import io.github.metheax.repository.ResourceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 11/07/2020
 */
@Component
public class MenuValidator extends AbstractMetheaValidator<MenuBinder> {

    private final ResourceRepository uriRepository;
    private final GroupRepository groupRepository;

    public MenuValidator(ResourceRepository uriRepository, MenuRepository repository,
                         GroupRepository groupRepository) {
        this.uriRepository = uriRepository;
        this.groupRepository = groupRepository;
        super.repository = repository;
    }

    @Override
    public void validate(MenuBinder binder, Map<String, String> errors) {
        Optional<TGroup> opt = groupRepository.findById(binder.getGroupId());
        if (!opt.isPresent()) {
            errors.put("groupId", "Error: Provided group invalid!!!");
        } else {
            binder.setGroupName(opt.get().getGroupName());
        }
        if (MetheaConstant.PARENT.equalsIgnoreCase(binder.getParentId())) {
            binder.setUriId(StringUtils.EMPTY);
            binder.setUriName(StringUtils.EMPTY);
        } else {
            Optional<TResource> optional = uriRepository.findById(binder.getUriId());
            if (!optional.isPresent()) {
                errors.put("uriId", "Error: Provided URL invalid!!!");
            } else {
               // binder.setUriName(optional.get().getUriName());
            }
        }
    }
}
