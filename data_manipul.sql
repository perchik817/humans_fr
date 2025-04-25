INSERT INTO Animals (name, birth_date) VALUES ('Sharik', '2022-04-01');
SET @last_id = LAST_INSERT_ID();

INSERT INTO Pets (id) VALUES (@last_id);
INSERT INTO Dogs (id, commands) VALUES (@last_id, 'sit, lie down');

DELETE FROM Camels;

CREATE OR REPLACE VIEW CombinedPackAnimals AS
SELECT * FROM Horses
UNION ALL
SELECT * FROM Donkeys;

CREATE OR REPLACE VIEW YoungAnimals AS
SELECT
    a.id,
    a.name,
    a.birth_date,
    TIMESTAMPDIFF(MONTH, a.birth_date, CURDATE()) AS age_in_months
FROM Animals a
WHERE TIMESTAMPDIFF(YEAR, a.birth_date, CURDATE()) BETWEEN 1 AND 3;

CREATE OR REPLACE VIEW AllAnimals AS
SELECT a.id, a.name, a.birth_date, 'Dog' AS type, d.commands
FROM Animals a
JOIN Dogs d ON a.id = d.id

UNION ALL

SELECT a.id, a.name, a.birth_date, 'Cat' AS type, c.commands
FROM Animals a
JOIN Cats c ON a.id = c.id

UNION ALL

SELECT a.id, a.name, a.birth_date, 'Hamster' AS type, h.commands
FROM Animals a
JOIN Hamsters h ON a.id = h.id

UNION ALL

SELECT a.id, a.name, a.birth_date, 'Horse' AS type, ho.commands
FROM Animals a
JOIN Horses ho ON a.id = ho.id

UNION ALL

SELECT a.id, a.name, a.birth_date, 'Camel' AS type, ca.commands
FROM Animals a
JOIN Camels ca ON a.id = ca.id

UNION ALL

SELECT a.id, a.name, a.birth_date, 'Donkey' AS type, do.commands
FROM Animals a
JOIN Donkeys do ON a.id = do.id;

