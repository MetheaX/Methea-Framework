package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.binder.ClientBinder;
import io.github.metheax.domain.entity.TClient;
import io.github.metheax.domain.view.ClientView;
import io.github.metheax.service.ClientService;
import io.github.metheax.utils.PrincipalUtils;
import io.github.metheax.utils.SystemUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 01/06/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {WebserviceClientController.ROOT_URL})
public class WebserviceClientController {

    static final String ROOT_URL = "/app/webservice-clients";

    private static final String SECRET_KEY = "clientSecret";

    private static final String CLIENT_TEMPLATE_PATH = "webservice/clients/webservice-client-list";
    private static final String CREATE_URL = "/create";
    private static final String REVOKE_CLIENT_URL = "/revoke-client";
    private static final String API_GET_BY_CLIENT_ID = "/view";

    private static final String ENTITY = "clients";

    private final ClientService clientService;

    @Inject
    public WebserviceClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = StringUtils.EMPTY)
    public String viewClients(Model model, HttpServletRequest request) {
        setAttributes(model, request);
        return CLIENT_TEMPLATE_PATH;
    }

    @PostMapping(value = CREATE_URL)
    public String create(ClientBinder binder, Model model, HttpServletRequest request) {
        TClient client = clientService.createOrUpdateClient(binder);
        setAttributes(model, request);
        model.addAttribute(SECRET_KEY, client.getOneTimeDisplaySecretKey());
        return CLIENT_TEMPLATE_PATH;
    }

    @PostMapping(value = REVOKE_CLIENT_URL)
    public ResponseEntity<Map<String, Object>> revokeClient(@RequestBody Map<String, String> payload) {
        Map<String, Object> map = new HashMap<>();

        try {
            clientService.revokeClient(payload.get("id"));
            map.put(MetheaConstant.JSON_STATUS, 200);
        } catch (Exception ex) {
            map.put(MetheaConstant.JSON_STATUS, 500);
            map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to get %s", payload.get("id")));
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = API_GET_BY_CLIENT_ID)
    public ResponseEntity<Map<String, Object>> getByClientId(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to get %s", id));
        TClient client = clientService.getClientByClientId(id);
        ClientBinder binder = new ClientBinder();
        if (!ObjectUtils.isEmpty(client)) {
            BeanUtils.copyProperties(client, binder);
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_DATA, binder);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private void setAttributes(Model model, HttpServletRequest request) {
        Map<String, List<ClientView>> map = new HashMap<>();
        map.put(MetheaConstant.JSON_DATA, clientService.getAllWebserviceClient());

        model.addAttribute(ENTITY, map);
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute(SECRET_KEY, StringUtils.EMPTY);
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        model.addAttribute(MetheaConstant.CORE_MENU, MetheaCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
    }
}
