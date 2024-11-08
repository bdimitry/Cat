
    CREATE SCHEMA cats;
    CREATE TABLE IF NOT EXISTS "cats"."cat"
    (
        name character varying(50) COLLATE pg_catalog."default",
        id SERIAL  NOT NULL,
        weight integer,
        age integer,
        CONSTRAINT "cat_pkey" PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS "cats"."image"
    (
        id SERIAL  NOT NULL,
        cat_photo BYTEA,
        FOREIGN KEY (id)
        REFERENCES cats.cat (id)
        ON DELETE CASCADE
    );

    CREATE TABLE IF NOT EXISTS "cats"."json_cat"
    (
        id BIGSERIAL PRIMARY KEY,
        cat JSONB,
        image_url VARCHAR(255)
    );