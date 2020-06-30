--17/06/2020  refactor de la part d'usuari pasamos ultima entidad a usuarioEntidad
ALTER TABLE CAR_USUARIO DROP COLUMN ULTIMA_ENTIDAD;

alter table CAR_USUARIO drop constraint CAR_USUARIO_ENTIDAD_FK;

DROP INDEX CAR_USUARIO_ENTIDAD_FK_I;

ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ULTIMA_ENTIDAD number(19,0);

alter table CAR_USUARIOENTIDAD
       add constraint CAR_USUENT_ULTENT_FK
       foreign key (ULTIMA_ENTIDAD)
       references CAR_ENTIDAD;

create index CAR_USUENT_ULTENT_FK_I on CAR_USUARIOENTIDAD (ULTIMA_ENTIDAD);

--tipo usuario
ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN TIPO number(19,0) not null;

-- Un plugin pot no estar relacionat amb una entitat
alter table CAR_PLUGIN modify ENTIDAD null;

-- 22/06/2020

--Idioma del usuario
ALTER TABLE CAR_USUARIO ADD COLUMN IDIOMA number(19,0) not null;

--Indica si es administrador
ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ADMINISTRADOR integer DEFAULT FALSE not null;


-- 23/06/2020

ALTER TABLE CAR_USUARIO DROP COLUMN IDIOMA;

ALTER TABLE CAR_USUARIO ADD COLUMN IDIOMA varchar2(10)  DEFAULT 'ca' NOT NULL;

ALTER TABLE CAR_USUARIOENTIDAD DROP COLUMN ADMINISTRADOR;

ALTER TABLE CAR_USUARIOENTIDAD ADD COLUMN ADMINENTIDAD integer DEFAULT 0 NOT NULL;

--30/06/2020

ALTER TABLE CAR_USUARIO ADD COLUMN DOCUMENTO VARCHAR2(255);