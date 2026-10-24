CREATE TABLE citas (
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    token             UUID NOT NULL UNIQUE,
    negocio_id        BIGINT NOT NULL REFERENCES negocios(id) ON DELETE CASCADE,
    fecha             DATE NOT NULL,
    hora              TIME NOT NULL,
    nombre_cliente    VARCHAR(255) NOT NULL,
    email_cliente     VARCHAR(255),
    telefono_cliente  VARCHAR(20),
    origen            VARCHAR(10) NOT NULL,
    estado            VARCHAR(20) NOT NULL,
    inicio_real       TIMESTAMP,
    fin_real          TIMESTAMP,
    creada_en         TIMESTAMP NOT NULL
);

-- Un hueco solo puede tener UNA cita activa.
-- Las canceladas no cuentan, para que el hueco vuelva a quedar libre.
CREATE UNIQUE INDEX uk_cita_hueco_activo
    ON citas (negocio_id, fecha, hora)
    WHERE estado <> 'CANCELADA';