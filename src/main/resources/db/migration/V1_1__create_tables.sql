create table users
(
    id            uuid not null primary key,
    name          VARCHAR(60),
    surname       VARCHAR(60),
    username      VARCHAR(55),
    active        BOOLEAN DEFAULT TRUE,
    password      VARCHAR(68) NOT NULL,
    created_at    timestamp default now() not null,
    updated_at    timestamp default now() not null

);

create table product
(
    id             SERIAL PRIMARY KEY NOT NULL,
    name           VARCHAR(60),
    price          float ,
    user_id uuid REFERENCES users (id) NOT NULL,
    created_at    timestamp default now() not null,
    updated_at    timestamp default now() not null


);

