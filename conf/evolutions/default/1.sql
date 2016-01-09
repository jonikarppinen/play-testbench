# --- !Ups

CREATE TABLE IF NOT EXISTS customer (
  id   BIGSERIAL PRIMARY KEY,
  name TEXT      NOT NULL
);

CREATE TABLE IF NOT EXISTS report (
  id          BIGSERIAL   PRIMARY KEY,
  customer_id BIGINT      REFERENCES customer(id) NOT NULL,
  name        TEXT        NOT NULL,
  created     TIMESTAMPTZ NOT NULL
);

# --- !Downs

DROP TABLE report;
DROP TABLE customer;