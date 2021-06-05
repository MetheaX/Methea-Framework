package io.methea.validator;

import io.methea.constant.MetheaConstant;
import io.methea.domain.entity.TGroup;
import io.methea.domain.binder.MenuBinder;
import io.methea.domain.entity.TResource;
import io.methea.repository.GroupRepository;
import io.methea.repository.MenuRepository;
import io.methea.repository.ResourceRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
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
