INSERT INTO user (name, surname) VALUES ('Matei', 'Popa');
INSERT INTO nfc (uid, user_id) VALUES ('abc123', 1);

INSERT INTO user (name, surname) VALUES ('Andrei', 'Popa');
INSERT INTO nfc (uid, user_id) VALUES ('123abc', 2);


INSERT INTO project (id, name) VALUES (1, 'Project Alpha');
INSERT INTO project (id, name) VALUES (2, 'Project Beta');

INSERT INTO project_user (project_id, user_id)
VALUES (1, 1), (1, 2);
