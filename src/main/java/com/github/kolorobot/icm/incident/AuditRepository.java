package com.github.kolorobot.icm.incident;

import java.util.List;

public interface AuditRepository {

    Audit save(Audit audit);

    List<Audit> findAll(Long incidentId);
}
