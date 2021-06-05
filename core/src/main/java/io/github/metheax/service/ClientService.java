package io.github.metheax.service;

import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.binder.ClientBinder;
import io.github.metheax.domain.entity.TClient;
import io.github.metheax.domain.view.ClientView;
import io.github.metheax.repository.ClientRepository;
import io.github.metheax.service.eventlistener.helper.InternalPermissionHelperService;
import io.github.metheax.utils.auth.Encryption;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final InternalPermissionHelperService helperService;

    @Inject
    public ClientService(ClientRepository clientRepository,
                         InternalPermissionHelperService helperService) {
        this.clientRepository = clientRepository;
        this.helperService = helperService;
    }

    @Transactional
    public TClient createOrUpdateClient(ClientBinder binder) {
        TClient client = getClientByClientId(binder.getClientId());
        if (ObjectUtils.isEmpty(client)) {
            client = new TClient();
            client.setId(UUID.randomUUID().toString());
            client.setClientId(binder.getClientId());
        }

        try {
            String rawSecret = Encryption.generateRandomPassword(64, '0', 'z');
            String clientSecret = new BCryptPasswordEncoder().encode(rawSecret);
            client.setClientSecret(clientSecret);
            client.setOneTimeDisplaySecretKey(rawSecret);
            client.setStatus(MetheaConstant.ACTIVE_STATUS);
            client.setStatus(MetheaConstant.ACTIVE_STATUS);

            //helperService.revokePermissionBaseOnClientID(client.getClientId());

            clientRepository.save(client);
            //helperService.saveClientPermission(client.getClientId(), binder.getApiBases());

            return client;
        } catch (Exception ex) {
            log.error("=========> createOrUpdateClient error: ", ex);
        }
        return null;
    }

    public TClient getClientByClientId(String s) {
        return clientRepository.findClientByClientIdAndStatus(s, MetheaConstant.ACTIVE_STATUS);
    }

    public List<ClientView> getAllWebserviceClient() {
        return clientRepository.getByQuery(new HashMap<>(), ClientView.class);
    }

    @Transactional
    public void revokeClient(String s) {
        clientRepository.revokeClient(s);
        //helperService.revokePermissionBaseOnClientID(s);
    }
}
