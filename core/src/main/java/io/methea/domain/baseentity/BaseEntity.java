package io.methea.domain.baseentity;


import io.methea.domain.baseentity.abs.AbstractEntity;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Author : DKSilverX
 * Date : 01/02/2020
 */
@MappedSuperclass
public class BaseEntity<E> extends AbstractEntity<E> {

    private static final long serialVersionUID = -8290479674879840950L;

    private String status;
    private String createdUser;
    private LocalDateTime createdDateTime;
    private String updatedUser;
    private LocalDateTime updatedDateTime;

    public BaseEntity() {
    }

    // Create
    public BaseEntity(String status, String createdUser, LocalDateTime createdDateTime, String updatedUser,
                      LocalDateTime updatedDateTime) {
        this.status = status;
        this.createdUser = createdUser;
        this.createdDateTime = createdDateTime;
        this.updatedUser = updatedUser;
        this.updatedDateTime = updatedDateTime;
    }

    // Update
    public BaseEntity(String updatedUser, LocalDateTime updatedDateTime) {
        this.updatedUser = updatedUser;
        this.updatedDateTime = updatedDateTime;
    }

    // Activate/Deactivate
    public BaseEntity(String status, String updatedUser,
                      LocalDateTime updatedDateTime) {
        this.status = status;
        this.updatedDateTime = updatedDateTime;
        this.updatedUser = updatedUser;
    }

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
