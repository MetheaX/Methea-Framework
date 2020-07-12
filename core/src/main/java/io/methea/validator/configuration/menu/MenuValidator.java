package io.methea.validator.configuration.menu;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.menu.dto.MenuBinder;
import io.methea.domain.configuration.uri.entity.TMstURI;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.menu.MenuRepository;
import io.methea.repository.configuration.uri.URIRepository;
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

    private final URIRepository uriRepository;
    private final UserGroupRepository groupRepository;

    public MenuValidator(URIRepository uriRepository, MenuRepository repository,
                         UserGroupRepository groupRepository) {
        this.uriRepository = uriRepository;
        this.groupRepository = groupRepository;
        super.repository = repository;
    }

    @Override
    public void validate(MenuBinder binder, Map<String, String> errors) {
        Optional<TUserGroup> opt = groupRepository.findById(binder.getGroupId());
        if (opt.isEmpty()) {
            errors.put("groupId", "Error: Provided group invalid!!!");
        } else {
            binder.setGroupName(opt.get().getGroupName());
        }
        if (MConstant.PARENT.equalsIgnoreCase(binder.getParentId())) {
            binder.setUriId(StringUtils.EMPTY);
            binder.setUriName(StringUtils.EMPTY);
        } else {
            Optional<TMstURI> optional = uriRepository.findById(binder.getUriId());
            if (optional.isEmpty()) {
                errors.put("uriId", "Error: Provided URL invalid!!!");
            } else {
                binder.setUriName(optional.get().getUriName());
            }
        }
    }
}
