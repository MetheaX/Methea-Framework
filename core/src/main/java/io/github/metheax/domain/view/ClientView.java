package io.github.metheax.domain.view;

import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 02/06/2020
 */
@SelectFrom(fromClause = "FROM Client o", orderBy = "ORDER BY o.clientId ASC")
public class ClientView extends BaseView<ClientView> {

    private static final long serialVersionUID = 6403050680058656926L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.clientId", isLastColumn = true)
    private String clientId;

    public ClientView(String id, String clientId) {
        this.id = id;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }
}
