package com.github.kolorobot.icm.incident;

import java.util.List;

interface AuditRepository {

    Audit save(Audit audit);

    List<Audit> findAll(Long incidentId);
}
