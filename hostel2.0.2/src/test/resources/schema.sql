CREATE TABLE IF NOT EXISTS WatchmenBabushka (
    id bigserial,
    name varchar(100) NOT NULL,
    CONSTRAINT "WatchmenBabushka_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Pass (
    id bigserial,
    sku varchar(100) NOT NULL,
    CONSTRAINT "Pass_pkey" PRIMARY KEY (id)
);