-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: db/migrations/changelog-master.json
-- Ran at: 12/28/24, 1:41 PM
-- Against: postgres@jdbc:postgresql://localhost:5432/local
-- Liquibase version: 4.30.0
-- *********************************************************************

-- Lock Database
UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 't14 (172.17.0.1)', LOCKGRANTED = NOW() WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

