﻿
BEGIN;
-- 13/01/2021 Es crearan auditories només de les accions del backoffice #278

ALTER TABLE car_auditoria DROP COLUMN usuariclave;
ALTER TABLE car_auditoria DROP COLUMN pluginid;

-- 13/01/2021 Nou camp icona per Plugin #297
ALTER TABLE car_plugin ADD logoid int8 NULL;
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_plugin_logoid_fk_i ON car_plugin using btree (logoid);

-- 14/01/2021 Refactorització d'Accesos #308
ALTER TABLE car_acces DROP COLUMN resultatautenticacio;
ALTER TABLE car_acces ADD COLUMN resultat boolean NOT NULL DEFAULT true;
ALTER TABLE car_acces ALTER COLUMN idioma SET NOT NULL;
ALTER TABLE car_acces RENAME datadarreracces  TO dataacces;
ALTER TABLE car_acces ADD COLUMN qaa integer;
ALTER TABLE car_acces RENAME nivellseguretat  TO metodeautenticacio;


-- 19/01/2021 Millores en la taula d'Auditories #304
ALTER TABLE car_auditoria ADD COLUMN objecte character varying(255);


--29/01/2021 Taula de Logs i Accessos a un altre TableSpace #315
ALTER TABLE car_log ALTER COLUMN error TYPE text;

COMMIT;

