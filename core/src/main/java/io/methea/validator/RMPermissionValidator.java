package io.methea.validator;

import io.methea.domain.binder.RMPermissionBinder;
import io.methea.domain.entity.TRole;
import io.methea.domain.entity.TUser;
import io.methea.repository.RoleRepository;
import io.methea.repository.UserRepository;
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
    private final RoleRepository userRoleRepository;

    public RMPermissionValidator(UserRepository userRepository, RoleRepository userRoleRepository) {
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
        if (!role.isPresent()) {
            errors.put("roleId", "Error: Provided role invalid!!!");
        } else {
            binder.setRoleName(role.get().getName());
        }
    }
}
