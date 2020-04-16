package io.methea.domain.configuration.group.projection;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;
import org.springframework.security.core.GrantedAuthority;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUser n, TUserGroup o")
public class GroupAuthorityProjection implements GrantedAuthority {
    @Column(name = "o.groupName", key ="username", where = "AND n.username = :username" +
            " AND n.groupId = o.id", isLastColumn = true)
    private String groupName;

    public GroupAuthorityProjection(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getAuthority() {
        return this.groupName;
    }
}
