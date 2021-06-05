package io.methea.repository;

import io.methea.domain.entity.TClient;
import io.methea.domain.view.ClientView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 03/05/2020
 */
public interface ClientRepository extends CrudRepository<TClient, String>,
        HibernateExtensionRepository<ClientView, String> {
    TClient findClientByClientId(String clientId);

    TClient findClientByClientIdAndStatus(String clientId, String status);

    @Modifying
    @Query("DELETE FROM TClient o WHERE o.clientId = ?1")
    void revokeClient(String clientId);
}
