package com.github.kolorobot.icm.webservice;

import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/user/{userId}/incident")
public class JerseyIncidentManagamentService {

    @Autowired
    private IncidentService incidentService;

    @PathParam("userId")
    private long userId;

    @GET
    @Produces("application/json")
    public List<Incident> getIncidents(@QueryParam("status") Incident.Status status) {
        return incidentService.getIncidents(userId, status);
    }
}
