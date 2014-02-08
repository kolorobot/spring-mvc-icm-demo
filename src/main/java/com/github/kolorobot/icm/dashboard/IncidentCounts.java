package com.github.kolorobot.icm.dashboard;

import com.github.kolorobot.icm.incident.Incident;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class IncidentCounts {

    private final String table;
    private final int all;
    private final int createdToday;
    private final Map<Incident.Status, Integer> byStatus;

    private IncidentCounts(String table, int all, int createdToday, Map<Incident.Status, Integer> byStatus) {
        this.table = table;
        this.all = all;
        this.createdToday = createdToday;
        this.byStatus = Collections.unmodifiableMap(byStatus);
    }

    public int all() {
        return all;
    }

    public int createdToday() {
        return createdToday;
    }

    public Map<Incident.Status, Integer> byStatus() {
        return byStatus;
    }

    public static class Builder {

        private String table;
        private int all;
        private int createdToday;
        private Map<Incident.Status, Integer> byStatus = Maps.newHashMap();

        public Builder(String table) {
            this.table = table;
        }

        public Builder setAll(int all) {
            this.all = all;
            return this;
        }

        public Builder setCreatedToday(int createdToday) {
            this.createdToday = createdToday;
            return this;
        }

        public void addStatus(Incident.Status status, int count) {
            this.byStatus.put(status, count);
        }

        public IncidentCounts build() {
            return new IncidentCounts(table, all, createdToday, byStatus);
        }
    }
}
