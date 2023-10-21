CREATE TABLE IF NOT EXISTS member.member_registry_datetime (
    id                  BIGSERIAL      PRIMARY KEY,
    member_id           uuid,
    created_at          timestamp(6)         NOT NULL            DEFAULT CURRENT_TIMESTAMP(6),

    CONSTRAINT  uq_account_registry_datetime_member_id          UNIQUE(member_id)
);

CREATE INDEX idx_member_registry_datetime_member_id ON member.member_registry_datetime(member_id);
