package io.github.metheax.repository;

import io.github.metheax.domain.entity.TClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Author : Kuylim Tith
 * Date : 03/05/2020
 */
public interface ClientRepository extends JpaRepository<TClient, String> {
    TClient findClientByClientId(String clientId);

    TClient findClientByClientIdAndStatus(String clientId, String status);

    @Modifying
    @Query("DELETE FROM TClient o WHERE o.clientId = ?1")
    void revokeClient(String clientId);
}
