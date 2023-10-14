create table IF NOT EXISTS member.member
(
    id              uuid    DEFAULT uuid_generate_v4 (),
    email           varchar(256),
    gender_type     varchar(30),
    name            varchar(50),
    nickname        varchar(50),
    password        varchar(256),
    username        varchar(50),

    CONSTRAINT		pk_member_id 		PRIMARY KEY(id),
    constraint      uq_username         unique (username)
    );

CREATE UNIQUE INDEX udx_member_username ON member(username);