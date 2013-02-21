package com.github.kolorobot.icm.incident;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
	@Query("SELECT i FROM Incident i WHERE i.creator.id = ?1 ORDER BY i.status ASC")
	List<Incident> findAll(Long accountId);
	
	@Query("SELECT i FROM Incident i WHERE i.id = ?1 AND i.creator.id = ?2")
	Incident findOne(Long id, Long accountId);
}
