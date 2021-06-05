package io.methea.domain.binder;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
public class WhiteURIPermissionBinder extends BaseBinder<WhiteURIPermissionBinder> {
    private static final long serialVersionUID = -2779611106268696516L;

    private String id = StringUtils.EMPTY;
    private String uriId = StringUtils.EMPTY;
    private String uriName = StringUtils.EMPTY;
    private List<String> allowedMethod = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public List<String> getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(List<String> allowedMethod) {
        this.allowedMethod = allowedMethod;
    }
}
