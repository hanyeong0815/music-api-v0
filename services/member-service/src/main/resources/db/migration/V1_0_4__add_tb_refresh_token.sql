create table IF NOT EXISTS member.refresh_token
(
    id          BIGSERIAL       not null,
    created_at  TIMESTAMP       not null,
    enabled     boolean         not null,
    expired_at  TIMESTAMP       not null,
    replaced_by varchar(128),
    subject     varchar(50)     not null,
    token       varchar(128),

    CONSTRAINT		pk_refresh_id   PRIMARY KEY(id),
    constraint      uk_token        unique (token)
);

create index idx_replaced on refresh_token (replaced_by);

create index idx_subject on refresh_token (subject);

create index idx_token on refresh_token (token);