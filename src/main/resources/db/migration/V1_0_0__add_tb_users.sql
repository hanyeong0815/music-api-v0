create table IF NOT EXISTS users
(
    id              BIGSERIAL,
    email           varchar(256),
    gender_type     varchar(30),
    name            varchar(50),
    nickname        varchar(50),
    password        varchar(256),
    username        varchar(50),

    CONSTRAINT		pk_users_id 		PRIMARY KEY(id),
    constraint      uq_username         unique (username)
);

CREATE UNIQUE INDEX udx_users_username ON users(username);