DELETE FROM votes;
DELETE FROM dishs;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('User1', 'password'),
       ('User2', 'admin'),
       ('Admin', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002);

INSERT INTO restaurants (name)
VALUES ('Avrora'),
       ('Balalayka'),
       ('Rostov');

INSERT INTO dishs (name,registered, price, restaurant_id)
VALUES ('Овсянка Avrora',       '2022-03-10 10:00:00',  500,    100003),
       ('капуста Avrora',       '2022-03-11 10:00:00',  1000,   100003),
       ('Пироги Balalayka',     '2022-03-10 10:00:00',  500,    100004),
       ('Вода Balalayka',       '2022-03-11 10:00:00',  100,    100004),
       ('Дичь Rostov',          '2022-03-10 10:00:00',  500,    100005),
       ('Красная рыба Rostov',  '2022-03-11 10:00:00',  1000,   100005),
       ('Мясо Rostov',          '2022-03-12 10:00:00',  510,    100005);

INSERT INTO votes (registered, user_id, restaurant_id)
VALUES ('2022-03-10 10:00:00',  100000,   100003),
       ('2022-03-11 10:00:00',  100000,   100003),
       ('2022-03-12 10:00:00',  100000,   100004),
       ('2022-03-10 10:00:00',  100001,   100003),
       ('2022-03-11 10:00:00',  100001,   100003),
       ('2022-03-10 10:00:00',  100002,   100005),
       ('2022-03-11 10:00:00',  100002,   100005);

