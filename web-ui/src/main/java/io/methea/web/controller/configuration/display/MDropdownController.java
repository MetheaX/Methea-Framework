package io.methea.web.controller.configuration.display;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.group.dropdown.GroupDropdown;
import io.methea.service.dropdown.MDropdownService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Kuylim TITH
 * Date: 12/8/2020
 */
@RestController
@RequestMapping(value = {MDropdownController.ROOT_URL})
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MDropdownController {
    static final String ROOT_URL = "/app/dropdown";
    private static final String GET_GROUP_DROPDOWN_BY_ACCOUNT = "/accounts/{id}/groups";

    private final MDropdownService dropdownService;

    @Inject
    public MDropdownController(MDropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }

    @GetMapping(value = GET_GROUP_DROPDOWN_BY_ACCOUNT)
    public ResponseEntity<Map<String, Object>> getGroupDropdownByAccount(@PathVariable("id") String accountId) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, "Failed to get dropdown group by account!");
        List<GroupDropdown> list = dropdownService.getGroupDropdownByAccount(accountId);
        if (null != list) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_MESSAGE, "Get dropdown group by account success!");
            map.put(MConstant.JSON_DATA, list);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
