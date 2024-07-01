-- liquibase formatted sql

-- changeset n.parkhomenko:V1696716408_create_table_refresh_tokens

CREATE TABLE refresh_tokens
(
    id          INT       NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id     INT       NOT NULL,
    token_type  INT       NOT NULL,
    token       VARCHAR   NOT NULL,
    expiry_date TIMESTAMP NOT NULL,

    CONSTRAINT refresh_tokens_user_id_token_type_unique UNIQUE(user_id, token_type),
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);