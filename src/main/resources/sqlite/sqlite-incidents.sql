INSERT INTO address (id, address_line, city_line) VALUES (1, "ul. Bruska 61", "85-422 Bydgoszcz");
INSERT INTO address (id, address_line, city_line) VALUES (2, "ul. Metalowa 82", "70-745 Szczecin");
INSERT INTO address (id, address_line, city_line) VALUES (3, "ul. Wtorkowa 86", "70-535 Szczecin");

INSERT INTO incident (id, incident_type, address_id, creator_id, assignee_id, description, created, status)
VALUES (1, "Rozszczelnione okno", 1, 3, NULL, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam mattis.", "2014-01-01 23:00:00", 0);

INSERT INTO incident (id, incident_type, address_id, creator_id, assignee_id, description, created, status)
VALUES (2, "Wyciek", 2, 3, NULL, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ", "2014-01-02 22:01:00", 2);

INSERT INTO incident (id, incident_type, address_id, creator_id, assignee_id, description, created, status)
VALUES (3, "Zapach gazu w kuchni", 3, 4, 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ", "2014-01-02 23:01:00", 1);

INSERT INTO audit (id, incident_id, creator_id, description, created, status, previous_status) VALUES (1, 2, 1, "", "2014-01-03 15:00:00", 1, 0);
INSERT INTO audit (id, incident_id, creator_id, description, created, status, previous_status) VALUES (2, 2, 1, "", "2014-01-03 15:00:00", 2, 1);
INSERT INTO audit (id, incident_id, creator_id, description, created, status, previous_status) VALUES (3, 3, 1, "", "2014-01-03 15:00:00", 1, 0);