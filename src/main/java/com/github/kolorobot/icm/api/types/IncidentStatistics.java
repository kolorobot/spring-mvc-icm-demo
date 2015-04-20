package com.github.kolorobot.icm.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kolorobot.icm.dashboard.IncidentCounts;
import com.github.kolorobot.icm.incident.Incident;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class IncidentStatistics {

    @XmlAttribute
    @JsonProperty
    private Integer createdToday;

    @XmlAttribute
    @JsonProperty
    private Integer all;

    @XmlElementWrapper(name = "byStatus")
    @XmlElement(name = "count")
    @JsonProperty(value = "count")
    private List<ByStatus> byStatus = new ArrayList<>();

    protected IncidentStatistics() {

    }

    public IncidentStatistics(IncidentCounts incidentCounts) {
        this.createdToday = incidentCounts.createdToday();
        this.all = incidentCounts.all();
        incidentCounts.byStatus().forEach((k, v) -> this.byStatus.add(new ByStatus(k, v)));
    }

    public static class ByStatus {

        @XmlAttribute
        private Incident.Status status;
        @XmlAttribute(name = "value")
        @JsonProperty(value = "value")
        private Integer count;

        protected ByStatus() {
        }

        public ByStatus(Incident.Status status, Integer count) {
            this.status = status;
            this.count = count;
        }

        public Incident.Status getStatus() {
            return status;
        }

        public Integer getCount() {
            return count;
        }
    }
}
