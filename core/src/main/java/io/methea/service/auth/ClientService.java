package io.methea.service.auth;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.Client;
import io.methea.domain.webservice.ClientCertificate;
import io.methea.domain.webservice.dto.ClientAuthentication;
import io.methea.domain.webservice.dto.ClientBinder;
import io.methea.repository.webservice.ClientCertificateRepository;
import io.methea.repository.webservice.ClientRepository;
import io.methea.utils.PrincipalUtils;
import io.methea.utils.auth.Encryption;
import io.methea.utils.auth.RsaKeyGenerate;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
@Service
public class ClientService {
    private static Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final ClientCertificateRepository certificateRepository;
    private final HttpServletRequest request;

    @Inject
    public ClientService(ClientRepository clientRepository, ClientCertificateRepository certificateRepository,
                         HttpServletRequest request) {
        this.clientRepository = clientRepository;
        this.certificateRepository = certificateRepository;
        this.request = request;
    }

    public Client createClient(ClientBinder binder) throws Exception {
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setClientId(binder.getClientId());

        String rawSecret = Encryption.generateRandomPassword(64, '0', 'z');
        String clientSecret = new BCryptPasswordEncoder().encode(rawSecret);
        log.info(">>>>> " + rawSecret);
        client.setClientSecret(clientSecret);
        client.setOneTimeDisplaySecretKey(rawSecret);
        client.setCreatedUser(PrincipalUtils.getUserLoginId(request));
        client.setUpdatedUser(PrincipalUtils.getUserLoginId(request));
        client.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        client.setUpdatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        client.setStatus(MConstant.ACTIVE_STATUS);

        KeyPair pair = new RsaKeyGenerate().createRsa();
        RSAPublicKey pubKey = (RSAPublicKey) pair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();

        client.setVerifyCode(Encryption.encrypt(rawSecret, pubKey));

        ClientCertificate certificate = new ClientCertificate();
        BeanUtils.copyProperties(client, certificate);
        certificate.setId(UUID.randomUUID().toString());
        certificate.setClientId(client.getClientId());
        certificate.setVerifyKey(Base64.encodeBase64String(privateKey.getEncoded()));

        clientRepository.save(client);
        certificateRepository.save(certificate);

        return client;
    }

    Client verifyClient(ClientAuthentication authentication) {
        Client client = clientRepository.findClientByClientId(Optional.ofNullable(authentication.getClientId()).orElse(StringUtils.EMPTY));
        if (ObjectUtils.isEmpty(client)) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(authentication.getClientSecret(), client.getClientSecret())
                && encoder.matches(decode(authentication), client.getClientSecret())) {
            return client;
        }
        return null;
    }

    private String decode(ClientAuthentication authentication) {
        ClientCertificate certificate = certificateRepository.findClientCertificateByClientId(authentication.getClientId());
        try {
            byte[] data = Base64.decodeBase64(certificate.getVerifyKey());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance(MConstant.RSA);
            RSAPrivateKey priKey = (RSAPrivateKey) fact.generatePrivate(spec);
            return Encryption.decrypt(authentication.getVerifyCode(), priKey);
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    Client getClientByClientId(String s){
        return clientRepository.findClientByClientId(s);
    }
}
