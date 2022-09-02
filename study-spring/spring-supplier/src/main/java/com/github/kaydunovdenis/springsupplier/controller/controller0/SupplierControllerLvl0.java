package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * According to Richardson Maturity Model
 * Rest controller level 0 is
 * - single URI
 * - single HTTP method
 */
@RestController
@RequestMapping("/controller0/supplier")
public class SupplierControllerLvl0 {

    /**
     * For implementation was using the pattern - Strategy
     * Across autowiring beans of RequestAction subclass to map
     */
    public final Map<String, RequestAction> requestActions;

    public SupplierControllerLvl0(Map<String, RequestAction> actions) {
        this.requestActions = actions;
    }

    @PostMapping()
    public Object request(RequestBody body) {
        String httpMethodKey = getHttpMethodKeyForBeanName(body);
        return requestActions.computeIfAbsent(httpMethodKey, key -> {
                    throw new IllegalStateException("Unexpected value for httpMethod: " + key);
                })
                .request(body.getSupplier());
    }

    private String getHttpMethodKeyForBeanName(RequestBody body) {
        return Optional.of(body)
                .orElseThrow(() -> new IllegalStateException("HttpMethod in Request body must not be null"))
                .getMethod()
                .name()
                .toLowerCase()
                .concat(RequestAction.class.getSimpleName());
    }
}
