--17/06/2020  refactor de la part d'usuari pasamos ultima entidad a usuarioEntidad

ALTER TABLE CAR_USUARIO DROP COLUMN ULTIMA_ENTIDAD;

alter table CAR_USUARIO drop constraint CAR_USUARIO_ENTIDAD_FK;

DROP INDEX CAR_USUARIO_ENTIDAD_FK_I;

ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ULTIMA_ENTIDAD bigint;

alter table CAR_USUARIOENTIDAD
       add constraint CAR_USUENT_ULTENT_FK
       foreign key (ULTIMA_ENTIDAD)
       references CAR_ENTIDAD;

create index CAR_USUENT_ULTENT_FK_I on CAR_USUARIOENTIDAD (ULTIMA_ENTIDAD);


ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN TIPO integer;

-- Un plugin pot no estar relacionat amb una entitat
ALTER TABLE CAR_PLUGIN ALTER COLUMN ENTIDAD DROP NOT NULL;

-- 22/06/2020  

--Idioma del usuario
ALTER TABLE CAR_USUARIO ADD COLUMN IDIOMA integer NOT NULL;

--Indica si es administrador
ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ADMINISTRADOR bool DEFAULT FALSE NOT NULL;

-- 23/06/2020

ALTER TABLE CAR_USUARIO DROP COLUMN IDIOMA;

ALTER TABLE CAR_USUARIO ADD COLUMN IDIOMA character varying (10)  DEFAULT 'ca' NOT NULL;

ALTER TABLE CAR_USUARIOENTIDAD DROP COLUMN ADMINISTRADOR;

ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ADMINENTIDAD bool DEFAULT FALSE NOT NULL;

--30/06/2020

ALTER TABLE CAR_USUARIO ADD COLUMN DOCUMENTO character varying (255);