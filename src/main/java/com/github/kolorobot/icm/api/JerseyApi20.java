package com.github.kolorobot.icm.api;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.api.types.IncidentStatistics;
import com.github.kolorobot.icm.api.types.NewAudit;
import com.github.kolorobot.icm.api.types.NewIncident;
import com.github.kolorobot.icm.incident.Audit;
import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Service
@Path("/")
public class JerseyApi20 {

    @Autowired
    private IncidentService incidentService;

    @GET
    @Path("/")
    @Produces({"application/json", "application/xml"})
    public IncidentStatistics getIncidentStatistics() {
        return new IncidentStatistics(incidentService.getIncidentCounts());
    }

    @GET
    @Path("user")
    @Produces({"application/json", "application/xml"})
    @XmlElementWrapper
    public List<Account> getUsers(@QueryParam("role") String role) {
        return incidentService.getAccountsInRole(role);
    }

    @GET
    @Path("user/{userId}")
    @Produces({"application/json", "application/xml"})
    public Account getUsers(@PathParam("userId") long userId) {
        return incidentService.getAccount(userId);
    }

    @GET
    @Path("user/{userId}/incident")
    @Produces({"application/json", "application/xml"})
    @XmlElementWrapper
    public List<Incident> getUserIncidents(@PathParam("userId") long userId,
                                           @QueryParam("status") Incident.Status status) {
        return incidentService.getUserIncidents(userId, status);
    }

    @POST
    @Path("user/{userId}/incident")
    @Produces({"application/json", "application/xml"})
    public Incident createUserIncident(@PathParam("userId") long userId,
                                        NewIncident incident) {

        if (userId != incident.getUserId()) {
            throw new IllegalArgumentException();
        }

        return incidentService.createIncident(userId,
                incident.getType(),
                incident.getDescription(),
                incident.getAddressLine(),
                incident.getCityLine());
    }

    @GET
    @Path("user/{userId}/incident/{incidentId}")
    @Produces({"application/json", "application/xml"})
    public Incident getUserIncident(@PathParam("userId") long userId,
                                     @PathParam("incidentId") long incidentId) {
        return incidentService.getUserIncident(userId, incidentId);
    }

    @DELETE
    @Path("user/{userId}/incident/{incidentId}")
    @Produces({"application/json", "application/xml"})
    public Response deleteUserIncident(@PathParam("userId") long userId,
                                     @PathParam("incidentId") long incidentId) {
        return Response.noContent().build();
    }

    @POST
    @Path("user/{userId}/incident/{incidentId}/audit")
    @Produces({"application/json", "application/xml"})
    public Audit getUserIncidentAudits(@PathParam("userId") long userId,
                                       @PathParam("incidentId") long incidentId,
                                       NewAudit audit) {

        if (userId != audit.getUserId()) {
            throw new IllegalArgumentException();
        }

        return incidentService.addUserIncidentAudit(
                userId,
                audit.getIncidentId(),
                audit.getAssigneeId(),
                audit.getNewStatus(),
                audit.getDescription());
    }

    @GET
    @Path("incident")
    @Produces({"application/json", "application/xml"})
    @XmlElementWrapper
    public List<Incident> getIncidents(@QueryParam("status") Incident.Status status) {
        return incidentService.getIncidents(status);
    }

    @POST
    @Path("incident")
    @Produces({"application/json", "application/xml"})
    public Incident createIncident(NewIncident incident) {

        return incidentService.createIncident(
                incident.getUserId(),
                incident.getType(),
                incident.getDescription(),
                incident.getAddressLine(),
                incident.getCityLine());
    }

    @GET
    @Path("incident/{incidentId}")
    @Produces({"application/json", "application/xml"})
    public Incident getIncident(@PathParam("incidentId") long incidentId) {
        return incidentService.getIncident(incidentId);
    }

    @GET
    @Path("incident/{incidentId}/audit")
    @Produces({"application/json", "application/xml"})
    @XmlElementWrapper
    public List<Audit> getIncidentAudits(@PathParam("incidentId") long incidentId) {
        return incidentService.getIncidentAudits(incidentId);
    }

    @POST
    @Path("incident/{incidentId}/audit")
    @Produces({"application/json", "application/xml"})
    public Audit getUserIncidentAudits(@PathParam("incidentId") long incidentId,
                                       NewAudit audit) {
        return incidentService.addUserIncidentAudit(
                audit.getUserId(),
                audit.getIncidentId(),
                audit.getAssigneeId(),
                audit.getNewStatus(),
                audit.getDescription());
    }
}
