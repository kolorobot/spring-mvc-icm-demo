package com.github.kolorobot.icm.api;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.api.types.IncidentStatistics;
import com.github.kolorobot.icm.api.types.NewAudit;
import com.github.kolorobot.icm.api.types.NewIncident;
import com.github.kolorobot.icm.files.File;
import com.github.kolorobot.icm.incident.Audit;
import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Service
@WebService
public class JaxWsApi10 {

    @Autowired
    private IncidentService incidentService;

    @WebMethod
    @XmlElement(name = "account")
    public List<Account> getAccounts(@WebParam(name = "role") String role) {
        return incidentService.getAccountsInRole(role);
    }

    @WebMethod
    @XmlElement(name = "statistics")
    public IncidentStatistics getIncidentStatistics() {
        return new IncidentStatistics(incidentService.getIncidentCounts());
    }

    @WebMethod
    @XmlElement(name = "incident")
    public List<Incident> search(@WebParam(name = "value") String value) {
        return incidentService.search(value);
    }

    @WebMethod
    @XmlElement(name = "incident")
    public List<Incident> getIncidents(@WebParam(name = "userId") long userId,
                                       @WebParam(name = "status") Incident.Status status) {
        return incidentService.getUserIncidents(userId, status);
    }

    @WebMethod
    @XmlElement(name = "incident")
    public Incident getIncident(@WebParam(name = "userId") long userId,
                                @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getUserIncident(userId, incidentId);
    }

    @WebMethod
    @XmlElement(name = "incident")
    public Incident addIncident(@WebParam(name = "incident", targetNamespace = "http://api.icm.com/incident") NewIncident incident) {
        return incidentService.createIncident(incident.getUserId(), incident.getType(), incident.getDescription(), incident.getAddressLine(), incident.getCityLine());
    }

    @WebMethod
    @XmlElement(name = "audit")
    public List<Audit> getIncidentAudits(@WebParam(name = "userId") long userId,
                                         @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getIncidentAudits(incidentService.getUserIncident(userId, incidentId).getId());
    }

    @WebMethod
    @XmlElement(name = "assignee")
    public List<Account> getAvailableIncidentAssignees(@WebParam(name = "userId") long userId,
                                                @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getAvailableUserIncidentAssignees(userId, incidentId);
    }

    @WebMethod
    @XmlElement(name = "transition")
    public List<Incident.Status> getAvailableIncidentTransitions(@WebParam(name = "userId") long userId,
                                                                 @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getAvailableUserIncidentTransitions(userId, incidentId);
    }

    @WebMethod
    @XmlElement(name = "audit")
    public Audit addIncidentAudit(@WebParam(name = "audit", mode = WebParam.Mode.IN) NewAudit audit) {
        return incidentService.addUserIncidentAudit(audit.getUserId(), audit.getIncidentId(), audit.getAssigneeId(), audit.getNewStatus(), audit.getDescription());
    }

    @WebMethod
    @XmlElement(name = "file")
    public List<File> getIncidentFiles(@WebParam(name = "userId") long userId,
                                       @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getIncidentFiles(incidentService.getUserIncident(userId, incidentId).getId());
    }
}
