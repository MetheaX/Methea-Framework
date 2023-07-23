package io.github.metheax.domain.view;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author : Kuylim Tith
 * Date : 16/04/2020
 */
public class GroupAuthorityView implements GrantedAuthority, Serializable {
    @Serial
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
