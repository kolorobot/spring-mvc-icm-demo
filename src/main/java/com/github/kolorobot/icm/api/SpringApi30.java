package com.github.kolorobot.icm.api;

import com.github.kolorobot.icm.api.types.IncidentStatistics;
import com.github.kolorobot.icm.incident.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v30", produces = {"application/json; charset=UTF-8", "application/xml; charset=UTF-8"})
public class SpringApi30 {

    @Autowired
    private IncidentService incidentService;

    @RequestMapping(value = "")
    public IncidentStatistics getIncidentStatistics() {
        return new IncidentStatistics(incidentService.getIncidentCounts());
    }
}
