package io.github.metheax.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "core_jwt_config")
public class TJWTConfig extends BaseEntity<TJWTConfig> {

    private static final long serialVersionUID = -7549497443834650618L;

    @Id
    @Column(name = "jwt_id", length = 36)
    private String id;
    @Column(name = "access_token_timeout", nullable = false)
    private long accessTokenTimeout;
    @Column(name = "refresh_token_timeout", nullable = false)
    private long refreshTokenTimeout;
    @Column(name = "token_type", nullable = false)
    private String tokenType;
    @Column(name = "header", nullable = false)
    private String header;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    public void setAccessTokenTimeout(long accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    public long getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    public void setRefreshTokenTimeout(long refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
