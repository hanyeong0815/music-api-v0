create table IF NOT EXISTS users_roles
(
    id          BIGSERIAL       not null,
    users_id    serial       not null,
    roles       varchar(50),

    CONSTRAINT pk_users_roles_id PRIMARY KEY(id),
    CONSTRAINT fk_users foreign key (users_id) references users(id)
);