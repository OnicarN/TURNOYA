CREATE TABLE negocios (
    id                     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_publico         UUID NOT NULL UNIQUE,
    nombre                 VARCHAR(255) NOT NULL,
    email                  VARCHAR(255) NOT NULL UNIQUE,
    password_hash          VARCHAR(255) NOT NULL,
    telefono               VARCHAR(20),
    hora_apertura          TIME NOT NULL,
    hora_cierre            TIME NOT NULL,
    duracion_cita_minutos  INTEGER NOT NULL
);

CREATE TABLE negocio_dias_abiertos (
    negocio_id  BIGINT NOT NULL REFERENCES negocios(id) ON DELETE CASCADE,
    dia         VARCHAR(10) NOT NULL,
    PRIMARY KEY (negocio_id, dia)
);