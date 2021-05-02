package io.methea.service.auth;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.client.entity.Client;
import io.methea.domain.webservice.client.dto.ClientBinder;
import io.methea.domain.webservice.client.view.ClientView;
import io.methea.repository.webservice.client.ClientRepository;
import io.methea.service.eventlistener.helper.InternalPermissionHelperService;
import io.methea.utils.auth.Encryption;
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
    public Client createOrUpdateClient(ClientBinder binder) {
        Client client = getClientByClientId(binder.getClientId());
        if (ObjectUtils.isEmpty(client)) {
            client = new Client();
            client.setId(UUID.randomUUID().toString());
            client.setClientId(binder.getClientId());
        }

        try {
            String rawSecret = Encryption.generateRandomPassword(64, '0', 'z');
            String clientSecret = new BCryptPasswordEncoder().encode(rawSecret);
            client.setClientSecret(clientSecret);
            client.setOneTimeDisplaySecretKey(rawSecret);
            client.setStatus(MConstant.ACTIVE_STATUS);
            client.setStatus(MConstant.ACTIVE_STATUS);

            //helperService.revokePermissionBaseOnClientID(client.getClientId());

            clientRepository.save(client);
            //helperService.saveClientPermission(client.getClientId(), binder.getApiBases());

            return client;
        } catch (Exception ex) {
            log.error("=========> createOrUpdateClient error: ", ex);
        }
        return null;
    }

    public Client getClientByClientId(String s) {
        return clientRepository.findClientByClientIdAndStatus(s, MConstant.ACTIVE_STATUS);
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
