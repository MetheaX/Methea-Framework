package io.methea.domain.webservice.view;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 02/06/2020
 */
@SelectFrom(fromClause = "FROM Client o", orderBy = "ORDER BY o.updatedDateTime DESC")
public class ClientView extends BaseView<ClientView> {

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.clientId")
    private String clientId;
    @Column(name = "o.verifyCode", isLastColumn = true)
    private String verifyCode;

    public ClientView(String id, String clientId, String verifyCode) {
        this.id = id;
        this.clientId = clientId;
        this.verifyCode = verifyCode;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }
}
