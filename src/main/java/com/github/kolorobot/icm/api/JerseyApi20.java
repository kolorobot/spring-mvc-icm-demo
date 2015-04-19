package com.github.kolorobot.icm.api;

import com.github.kolorobot.icm.api.types.IncidentStatistics;
import com.github.kolorobot.icm.dashboard.IncidentCountsRepository;
import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/")
public class JerseyApi20 {

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private IncidentCountsRepository incidentCountsRepository;

    @WebMethod
    @Produces({"application/json", "application/xml"})
    public IncidentStatistics getIncidentStatistics() {
        return new IncidentStatistics(incidentCountsRepository.incidentCounts());
    }

    @Path("/user/{userId}/incident")
    @GET
    @Produces({"application/json", "application/xml"})
    public List<Incident> getIncidents(@PathParam("userId") long userId, @QueryParam("status") Incident.Status status) {
        return incidentService.getIncidents(userId, status);
    }
}
