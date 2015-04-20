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

    @GET
    @Produces({"application/json", "application/xml"})
    public IncidentStatistics getIncidentStatistics() {
        return new IncidentStatistics(incidentService.getIncidentCounts());
    }
}
