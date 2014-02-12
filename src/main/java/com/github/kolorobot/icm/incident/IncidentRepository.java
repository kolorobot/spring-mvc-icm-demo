package com.github.kolorobot.icm.incident;

import java.util.List;

interface IncidentRepository {

	List<Incident> findAll();

    List<Incident> findAllByStatus(Incident.Status status);

    List<Incident> search(String queryString);

	Incident findOne(Long id);

	Incident findOneByIdAndCreatorId(Long id, Long accountId);

	Incident findOneByIdAndAssigneeIdOrCreatorId(Long id, Long accountId);

    Incident save(Incident incident);

    Incident update(Incident incident);
}
