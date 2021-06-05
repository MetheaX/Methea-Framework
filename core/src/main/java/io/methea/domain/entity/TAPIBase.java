package io.methea.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@Entity
@Table(name = "tbl_api_base")
public class TAPIBase extends BaseEntity<TAPIBase> {
    private static final long serialVersionUID = -8764618608450291555L;

    @Id
    @Column(name = "api_id")
    private String id;

    @Column(name = "api_url_name", nullable = false)
    private String apiUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
