package io.methea.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "core_session_management")
public class TSessionManagement extends BaseEntity<TSessionManagement> {

    private static final long serialVersionUID = 6839737862614212682L;

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "user_login_id", nullable = false)
    private String userLoginId;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "is_logout", nullable = false)
    private boolean isLogout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isLogout() {
        return isLogout;
    }

    public void setLogout(boolean logout) {
        isLogout = logout;
    }
}
