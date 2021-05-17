create table PersonalizedFact
(
    id         uuid    not null,
    createdAt  timestamp,
    deleted    boolean not null,
    factId     varchar(255),
    randomness float8,
    source     varchar(255),
    feedback   varchar(255),
    sentCount  int4    not null,
    verified   boolean not null,
    text       TEXT,
    type       varchar(255),
    updatedAt  timestamp,
    used       boolean not null,
    primary key (id)
)