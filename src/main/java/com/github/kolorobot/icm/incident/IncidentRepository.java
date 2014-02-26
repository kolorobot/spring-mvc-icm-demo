package com.github.kolorobot.icm.incident;

import java.util.List;

public interface IncidentRepository {

	List<Incident> findAll();

    List<Incident> findAllByStatus(Incident.Status status);

    List<Incident> findAllByCreatorIdAndStatus(Long accountId, Incident.Status status);

    List<Incident> findAllByCreatorId(Long accountId);

    List<Incident> findAllByAssigneeIdOrCreatorId(Long accountId);

    List<Incident> findAllByAssigneeIdOrCreatorIdAndStatus(Long accountId, Incident.Status status);

    List<Incident> search(String queryString);

	Incident findOne(Long id);

	Incident findOneByIdAndCreatorId(Long id, Long accountId);

	Incident findOneByIdAndAssigneeIdOrCreatorId(Long id, Long accountId);

    Incident save(Incident incident);

    Incident update(Incident incident);
}
