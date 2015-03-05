package com.github.kolorobot.icm.support.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@Component
public class EndpointsMvcEndpoint extends EndpointMvcAdapter {

    private final EndpointsEndpoint delegate;

    @Autowired
    public EndpointsMvcEndpoint(EndpointsEndpoint delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    @ResponseBody
    public Set<Endpoint> filter(@RequestParam(required = false) Boolean enabled,
                                @RequestParam(required = false) Boolean sensitive) {
        Predicate<Endpoint> isEnabled =
                endpoint -> matches(endpoint::isEnabled, ofNullable(enabled));

        Predicate<Endpoint> isSensitive =
                endpoint -> matches(endpoint::isSensitive, ofNullable(sensitive));

        return this.delegate.invoke().stream()
                .filter(isEnabled.and(isSensitive))
                .collect(toSet());
    }

    private <T> boolean matches(Supplier<T> supplier, Optional<T> value) {
        return !value.isPresent() || supplier.get().equals(value.get());
    }
}