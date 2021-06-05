package io.methea.domain.view;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;
import org.springframework.security.core.GrantedAuthority;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUser n, TUserGroup o")
public class GroupAuthorityView extends BaseView<GroupAuthorityView> implements GrantedAuthority {
    private static final long serialVersionUID = 3730481416886472698L;
    @Column(name = "o.groupName", key ="username", where = "AND n.username = :username" +
            " AND n.groupId = o.id", isLastColumn = true)
    private String groupName;

    public GroupAuthorityView(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getAuthority() {
        return this.groupName;
    }
}
