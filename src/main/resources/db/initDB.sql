DROP TABLE IF EXISTS user_restaurant_votes;
DROP TABLE IF EXISTS restaurant_dishs;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS restaurant_dishs;
DROP TABLE IF EXISTS user_restaurant_votes;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL
);

CREATE TABLE restaurant_dishs
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name          VARCHAR                           NOT NULL,
    registered    TIMESTAMP           DEFAULT now() NOT NULL,
    price         INT                               NOT NULL,
    id_restaurant INTEGER                           NOT NULL,
    FOREIGN KEY (id_restaurant) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE user_restaurant_votes
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    registered    TIMESTAMP           DEFAULT now() NOT NULL,
    id_user       INTEGER                           NOT NULL,
    id_restaurant INTEGER                           NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_restaurant) REFERENCES restaurants (id) ON DELETE CASCADE

);
