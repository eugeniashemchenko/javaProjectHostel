CREATE TABLE IF NOT EXISTS Watchman (
    id bigserial,
    name varchar(100) NOT NULL,
    CONSTRAINT "Watchman_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Student (
    id bigserial,
    sku varchar(100) NOT NULL,
    CONSTRAINT "Student_pkey" PRIMARY KEY (id)
);