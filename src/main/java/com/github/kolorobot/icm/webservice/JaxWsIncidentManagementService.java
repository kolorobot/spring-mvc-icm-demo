package com.github.kolorobot.icm.webservice;

import com.github.kolorobot.icm.files.File;
import com.github.kolorobot.icm.incident.Audit;
import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@Service
@WebService
public class JaxWsIncidentManagementService {

    @Autowired
    private IncidentService incidentService;

    @WebMethod
    public List<Incident> search(@WebParam(name = "value") String value) {
        return incidentService.search(value);
    }

    @WebMethod
    public List<Incident> getIncidents(@WebParam(name = "userId") long userId,
                                       @WebParam(name = "status") Incident.Status status) {
        return incidentService.getIncidents(userId, status);
    }

    @WebMethod
    public Incident getIncident(@WebParam(name = "userId") long userId,
                                @WebParam(name = "incidentId") long incidentId) {
        return incidentService.getIncident(userId, incidentId);
    }

    @WebMethod
    public List<Audit> getAudits(@WebParam(name = "incidentId") long incidentId) {
        return incidentService.getAudits(incidentId);
    }

    @WebMethod
    public List<File> getFiles(@WebParam(name = "incidentId") long incidentId) {
        return incidentService.getFiles(incidentId);
    }

}
