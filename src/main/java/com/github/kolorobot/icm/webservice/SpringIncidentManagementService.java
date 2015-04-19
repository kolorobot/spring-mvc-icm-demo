package com.github.kolorobot.icm.webservice;

import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v30")
public class SpringIncidentManagementService {

    @Autowired
    private IncidentService incidentService;

    @RequestMapping(value = "user/{userId}/incident", produces = {"application/json"})
    public List<Incident> getIncidents(@PathVariable("userId") Long userId,
                                       @RequestParam Incident.Status status) {
        return incidentService.getIncidents(userId, status);
    }

}
