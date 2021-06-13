package io.github.metheax.domain.view;

import org.springframework.security.core.GrantedAuthority;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
public class GroupAuthorityView extends BaseView<GroupAuthorityView> implements GrantedAuthority {
    private static final long serialVersionUID = 3730481416886472698L;
    private String groupCode;

    public GroupAuthorityView(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String getAuthority() {
        return this.groupCode;
    }
}
