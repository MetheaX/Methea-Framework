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
@Table(name = "core_session_management")
public class SessionManagement extends BaseEntity<SessionManagement> {

    @Serial
    private static final long serialVersionUID = 6839737862614212682L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "is_logout", nullable = false)
    private boolean isLogout;
}
