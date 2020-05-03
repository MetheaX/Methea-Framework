package io.methea.repository.webservice;

import io.methea.domain.webservice.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public interface ClientRepository extends CrudRepository<Client, String> {
    Client findClientByClientId(String clientId);
}
