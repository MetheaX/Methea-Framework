package io.methea.repository.webservice.client;

import io.methea.domain.webservice.client.entity.Client;
import io.methea.domain.webservice.client.view.ClientView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public interface ClientRepository extends CrudRepository<Client, String>,
        HibernateExtensionRepository<ClientView, String> {
    Client findClientByClientId(String clientId);

    Client findClientByClientIdAndStatus(String clientId, String status);

    @Modifying
    @Query("DELETE FROM Client o WHERE o.clientId = ?1")
    void revokeClient(String clientId);
}
