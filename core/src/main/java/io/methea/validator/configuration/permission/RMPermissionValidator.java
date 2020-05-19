package io.methea.validator.configuration.permission;

import io.methea.domain.configuration.permission.dto.RMPermissionBinder;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
@Component
public class RMPermissionValidator extends AbstractMetheaValidator<RMPermissionBinder> {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public RMPermissionValidator(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void validate(RMPermissionBinder binder, Map<String, String> errors) {
        TUser user = userRepository.findByUsername(binder.getUserLoginId());
        if (ObjectUtils.isEmpty(user)) {
            errors.put("userLoginId", "Error: Provided user invalid!!!");
        } else {
            binder.setFirstName(user.getFirstName());
            binder.setLastName(user.getLastName());
        }
        Optional<TRole> role = userRoleRepository.findById(binder.getRoleId());
        if (role.isEmpty()) {
            errors.put("roleId", "Error: Provided role invalid!!!");
        } else {
            binder.setRoleName(role.get().getName());
        }
    }
}
