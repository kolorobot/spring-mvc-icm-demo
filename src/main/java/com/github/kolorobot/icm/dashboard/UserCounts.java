package com.github.kolorobot.icm.dashboard;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class UserCounts {

    private final int all;
    private final Map<String, Integer> byRole;

    private UserCounts(int all, Map<String, Integer> byRole) {
        this.all = all;
        this.byRole = Collections.unmodifiableMap(byRole);
    }

    public int all() {
        return all;
    }

    public Map<String, Integer> byRole() {
        return byRole;
    }

    public static class Builder {
        private int all;
        private Map<String, Integer> byRole = Maps.newHashMap();

        public Builder setAll(int all) {
            this.all = all;
            return this;
        }

        public Builder addRole(String role, int count) {
            this.byRole.put(role, count);
            return this;
        }

        public UserCounts build() {
            return new UserCounts(all, byRole);
        }
    }
}
