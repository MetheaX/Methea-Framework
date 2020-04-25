package io.methea.domain.configuration.user.view;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@SelectFrom(fromClause = "FROM TUser o, TUserGroup p", orderBy = "ORDER BY o.updatedDateTime DESC")
public class UserView extends BaseView<UserView> {
    private static final long serialVersionUID = 1458249184346892136L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.username", key = "username", where = "AND LOWER(o.username) LIKE :username")
    private String username;
    @Column(name = "p.groupName", key = "groupName", where = "AND LOWER(p.groupName) LIKE :groupName")
    private String groupName;
    @Column(name = "o.firstName", key = "firstName", where = "AND LOWER(o.firstName) LIKE :firstName")
    private String firstName;
    @Column(name = "o.lastName", key = "lastName", where = "AND LOWER(o.lastName) LIKE :lastName")
    private String lastName;
    @Column(name = "o.phone", key = "phone", where = "AND LOWER(o.phone) LIKE :phone")
    private String phone;
    @Column(name = "o.email", key = "email", where = "AND LOWER(o.email) LIKE :email")
    private String email;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status " +
            "AND o.groupId = p.id", isLastColumn = true)
    private String status;

    public UserView(String id, String username, String groupName, String firstName, String lastName, String phone,
                    String email, String status) {
        this.id = id;
        this.username = username;
        this.groupName = groupName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
