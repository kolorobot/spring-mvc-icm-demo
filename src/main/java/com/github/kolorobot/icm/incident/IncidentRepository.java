package com.github.kolorobot.icm.incident;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
