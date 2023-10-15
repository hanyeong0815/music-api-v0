create table IF NOT EXISTS member.member_roles
(
    id          BIGSERIAL       not null,
    member_id   uuid            not null,
    roles       varchar(50),

    CONSTRAINT pk_member_roles_id PRIMARY KEY(id),
    CONSTRAINT fk_member foreign key (member_id) references member.member(id)
);