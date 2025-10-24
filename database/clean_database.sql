-- Script para limpar todas as tabelas do banco de dados F1

-- Desabilitar constraints temporariamente
ALTER TABLE driver_substitutions DISABLE TRIGGER ALL;
ALTER TABLE race_results DISABLE TRIGGER ALL;
ALTER TABLE cars DISABLE TRIGGER ALL;
ALTER TABLE engineers DISABLE TRIGGER ALL;
ALTER TABLE drivers DISABLE TRIGGER ALL;
ALTER TABLE team_bosses DISABLE TRIGGER ALL;
ALTER TABLE championship_standings DISABLE TRIGGER ALL;
ALTER TABLE races DISABLE TRIGGER ALL;
ALTER TABLE teams DISABLE TRIGGER ALL;

-- Limpar todas as tabelas
TRUNCATE TABLE driver_substitutions CASCADE;
TRUNCATE TABLE race_results CASCADE;
TRUNCATE TABLE cars CASCADE;
TRUNCATE TABLE engineers CASCADE;
TRUNCATE TABLE drivers CASCADE;
TRUNCATE TABLE team_bosses CASCADE;
TRUNCATE TABLE championship_standings CASCADE;
TRUNCATE TABLE races CASCADE;
TRUNCATE TABLE teams CASCADE;

-- Reabilitar constraints
ALTER TABLE driver_substitutions ENABLE TRIGGER ALL;
ALTER TABLE race_results ENABLE TRIGGER ALL;
ALTER TABLE cars ENABLE TRIGGER ALL;
ALTER TABLE engineers ENABLE TRIGGER ALL;
ALTER TABLE drivers ENABLE TRIGGER ALL;
ALTER TABLE team_bosses ENABLE TRIGGER ALL;
ALTER TABLE championship_standings ENABLE TRIGGER ALL;
ALTER TABLE races ENABLE TRIGGER ALL;
ALTER TABLE teams ENABLE TRIGGER ALL;

-- Resetar sequências
ALTER SEQUENCE teams_id_seq RESTART WITH 1;
ALTER SEQUENCE drivers_id_seq RESTART WITH 1;
ALTER SEQUENCE engineers_id_seq RESTART WITH 1;
ALTER SEQUENCE cars_id_seq RESTART WITH 1;
ALTER SEQUENCE races_id_seq RESTART WITH 1;
ALTER SEQUENCE race_results_id_seq RESTART WITH 1;
ALTER SEQUENCE driver_substitutions_id_seq RESTART WITH 1;
ALTER SEQUENCE championship_standings_id_seq RESTART WITH 1;
ALTER SEQUENCE team_bosses_id_seq RESTART WITH 1;

SELECT 'Database cleaned successfully!' as status;

