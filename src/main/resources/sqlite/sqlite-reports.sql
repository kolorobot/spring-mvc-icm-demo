-- incident count
select count(id) from incident;

-- created today
select count(id) from incident where created >= date('now');

-- incident statuses count
select count(incident.id), status.value, status.ordinal from status
  left outer join incident on incident.status = status.ordinal group by status.ordinal;

-- audit statuses count
select count(audit.id), status.value, status.ordinal from status
  left outer join audit on audit.status = status.ordinal group by status.ordinal;

-- user count
select count(id) from account;

-- user by role count
select count(account.role), user_roles.value from user_roles
  left outer join account on account.role = user_roles.value group by user_roles.value;