package io.github.metheax.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "core_jwt_config")
public class JWTConfig extends BaseEntity<JWTConfig> {

    @Serial
    private static final long serialVersionUID = -7549497443834650618L;

    @Id
    @Column(name = "jwt_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "access_token_timeout", nullable = false)
    private long accessTokenTimeout;
    @Column(name = "refresh_token_timeout", nullable = false)
    private long refreshTokenTimeout;
    @Column(name = "token_type", nullable = false)
    private String tokenType;
    @Column(name = "header", nullable = false)
    private String header;
}
