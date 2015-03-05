package com.github.kolorobot.icm.support.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndpointsEndpoint extends AbstractEndpoint<List<Endpoint>> {

    private List<Endpoint> endpoints;

    @Autowired
    public EndpointsEndpoint(List<Endpoint> endpoints) {
        super("endpoints");
        this.endpoints = endpoints;
    }


    @Override
    public List<Endpoint> invoke() {
        return endpoints;
    }
}
