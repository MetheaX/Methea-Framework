package io.github.metheax.validator;

import io.github.metheax.domain.binder.RMPermissionBinder;
import io.github.metheax.domain.entity.TRole;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.repository.RoleRepository;
import io.github.metheax.repository.UserRepository;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
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
