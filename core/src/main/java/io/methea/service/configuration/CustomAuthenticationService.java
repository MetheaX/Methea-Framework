package io.methea.service.configuration;

import io.methea.domain.configuration.user.entity.TUser;
import io.methea.repository.configuration.permission.UserGrantedRepository;
import io.methea.repository.configuration.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Service
@Transactional
public class CustomAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserGrantedRepository userGrantedRepository;

    @Inject
    public CustomAuthenticationService(UserRepository userRepository, UserGrantedRepository userGrantedRepository) {
        this.userRepository = userRepository;
        this.userGrantedRepository = userGrantedRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsername(s);

        if(ObjectUtils.isEmpty(user)){
            return null;
        }
        return new User(user.getUsername(), user.getPassword(), userGrantedRepository.findAllByUserId(user.getUsername()));
    }
}
