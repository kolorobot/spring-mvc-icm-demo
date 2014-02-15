-- incident count
select count(id) from incident;

-- created today
select count(id) from incident where created >= date('now');

-- incident statuses count
select count(incident.id), status.value, status.ordinal from status
  left outer join incident on incident.status = status.ordinal group by status.ordinal;

-- audit count
select count(id) from audit;

-- audit today
select count(id) from audit where created >= date('now');

-- audit statuses count
select count(audit.id), status.value, status.ordinal from status
  left outer join audit on audit.status = status.ordinal group by status.ordinal;

-- user count
select count(id) from account;

-- user by role count
select count(account.role), user_roles.value from user_roles
  left outer join account on account.role = user_roles.value group by user_roles.value;

-- all incidents

select
  incident.id as incident_id,
  incident.created as incident_created,
  incident.incident_type as incident_type,
  incident.description as incident_description,
  incident.status as incident_status,
  incident.creator_id as creator_id,
  creator.email as creator_email,
  creator.phone as creator_phone,
  incident.assignee_id as assignee_id,
  assignee.email as assignee_email,
  assignee.phone as assignee_phone,
  address.id as address_id,
  address.address_line as address_line,
  address.city_line as address_city_line,
  audit.id as audit_id,
  audit.created as audit_created,
  audit.previous_status as audit_previous_status,
  audit.status as audit_status
from incident
  left outer join address on incident.address_id = address.id
  left outer join audit on incident.id = audit.incident_id
  left outer join account as creator on incident.creator_id = creator.id
  left outer join account as assignee on incident.assignee_id = assignee.id;