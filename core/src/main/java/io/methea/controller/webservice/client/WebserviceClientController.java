package io.methea.controller.webservice.client;

import io.methea.constant.MConstant;
import io.methea.domain.webservice.Client;
import io.methea.domain.webservice.dto.ClientBinder;
import io.methea.domain.webservice.view.ClientView;
import io.methea.service.auth.ClientService;
import io.methea.utils.SystemUtils;
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

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public String viewClients(Model model, HttpServletRequest request) {
        Map<String, List<ClientView>> map = new HashMap<>();
        map.put(MConstant.JSON_DATA, clientService.getAllWebserviceClient());

        model.addAttribute(ENTITY, map);
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute(SECRET_KEY, StringUtils.EMPTY);
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return CLIENT_TEMPLATE_PATH;
    }

    @RequestMapping(value = CREATE_URL, method = RequestMethod.POST)
    public String create(ClientBinder binder, Model model, HttpServletRequest request) {
        Client client = clientService.createOrUpdateClient(binder);
        Map<String, List<ClientView>> map = new HashMap<>();
        map.put(MConstant.JSON_DATA, clientService.getAllWebserviceClient());

        model.addAttribute(ENTITY, map);
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute(SECRET_KEY, client.getOneTimeDisplaySecretKey());
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return CLIENT_TEMPLATE_PATH;
    }

    @RequestMapping(value = REVOKE_CLIENT_URL, method = RequestMethod.GET)
    public String revokeClient(@RequestParam String id, Model model, HttpServletRequest request) {
        clientService.revokeClient(id);
        model.addAttribute(SECRET_KEY, StringUtils.EMPTY);
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return CLIENT_TEMPLATE_PATH;
    }

    @ResponseBody
    @GetMapping(value = API_GET_BY_CLIENT_ID)
    public ResponseEntity<Map<String, Object>> getByClientId(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, String.format("Failed to get %s", id));
        Client client = clientService.getClientByClientId(id);
        ClientBinder binder = new ClientBinder();
        if (!ObjectUtils.isEmpty(client)) {
            BeanUtils.copyProperties(client, binder);
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_DATA, binder);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
