package com.github.kolorobot.icm.api;

import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v30")
public class SpringApi30 {

    @Autowired
    private IncidentService incidentService;

    @RequestMapping(value = "user/{userId}/incident")
    public List<Incident> getUserIncidents(@PathVariable("userId") long userId) {
        return incidentService.getUserIncidents(userId, null);
    }

}
