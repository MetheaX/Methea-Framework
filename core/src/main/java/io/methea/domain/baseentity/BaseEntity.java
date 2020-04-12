package io.methea.domain.baseentity;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Author : DKSilverX
 * Date : 01/02/2020
 */
@MappedSuperclass
public class BaseEntity {
    private String status;
    private String createdUser;
    private LocalDateTime createdDateTime;
    private String updatedUser;
    private LocalDateTime updatedDateTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
