DELETE FROM meals;

ALTER SEQUENCE global_seq_m RESTART WITH 1000;

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2015-08-20 12:20:48', 'food 1', 1500, 100000);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2015-08-20 16:20:48', 'food 2', 1000, 100000);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2015-08-20 19:20:48', 'food 3', 1800, 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2015-08-20 19:20:48', 'food 4', 1700, 100001);