package io.methea.domain.baseentity;


import io.methea.domain.baseentity.abs.AbstractMetheaEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Author : DKSilverX
 * Date : 01/02/2020
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<E> extends AbstractMetheaEntity<E> {

    private static final long serialVersionUID = -8290479674879840950L;

    @Column(name = "status", nullable = false)
    private String status;
    @CreatedBy
    @Column(name = "created_user", nullable = false)
    private String createdUser;
    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;
    @LastModifiedBy
    @Column(name = "updated_user", nullable = false)
    private String updatedUser;
    @UpdateTimestamp
    @Column(name = "updated_date_time", nullable = false)
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
