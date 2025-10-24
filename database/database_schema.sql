-- Script SQL para criar as tabelas do projeto F1
-- Banco de dados: PostgreSQL

-- Tabela de Equipes
CREATE TABLE IF NOT EXISTS teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    citizenship VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Chefes de Equipe (Team Boss)
CREATE TABLE IF NOT EXISTS team_bosses (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    wage DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Tabela de Pilotos (Drivers)
CREATE TABLE IF NOT EXISTS drivers (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL CHECK (age >= 16),
    wage DOUBLE PRECISION NOT NULL,
    car_number INTEGER NOT NULL UNIQUE,
    handicap INTEGER DEFAULT 0 CHECK (handicap >= 0),
    points_of_season INTEGER DEFAULT 0,
    is_reserve BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Tabela de Engenheiros (Engineers)
CREATE TABLE IF NOT EXISTS engineers (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL CHECK (age >= 16),
    wage DOUBLE PRECISION NOT NULL,
    speciality VARCHAR(100) NOT NULL,
    years_of_experience INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Tabela de Carros (Cars)
CREATE TABLE IF NOT EXISTS cars (
    id SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL,
    model VARCHAR(100) NOT NULL,
    horse_power INTEGER NOT NULL,
    aerodynamic_coefficient DOUBLE PRECISION NOT NULL,
    performance DOUBLE PRECISION NOT NULL,
    driver_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES drivers(id) ON DELETE SET NULL
);

-- Tabela de Corridas (Races)
CREATE TABLE IF NOT EXISTS races (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    emoji VARCHAR(10),
    round_number INTEGER,
    season_year INTEGER DEFAULT 2025,
    is_simulated BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Resultados das Corridas (Race Results)
CREATE TABLE IF NOT EXISTS race_results (
    id SERIAL PRIMARY KEY,
    race_id INTEGER NOT NULL,
    driver_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    finish_position INTEGER NOT NULL,
    points_earned INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (race_id) REFERENCES races(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES drivers(id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Tabela de Histórico de Substituições de Pilotos
CREATE TABLE IF NOT EXISTS driver_substitutions (
    id SERIAL PRIMARY KEY,
    race_id INTEGER,
    team_id INTEGER NOT NULL,
    driver_removed_id INTEGER NOT NULL,
    driver_added_id INTEGER NOT NULL,
    reason VARCHAR(255),
    substitution_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (race_id) REFERENCES races(id) ON DELETE SET NULL,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_removed_id) REFERENCES drivers(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_added_id) REFERENCES drivers(id) ON DELETE CASCADE
);

-- Tabela de Classificação do Campeonato (Championship Standing)
CREATE TABLE IF NOT EXISTS championship_standings (
    id SERIAL PRIMARY KEY,
    season_year INTEGER DEFAULT 2025,
    team_id INTEGER NOT NULL,
    total_points INTEGER DEFAULT 0,
    races_completed INTEGER DEFAULT 0,
    position INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Índices para melhorar performance
CREATE INDEX IF NOT EXISTS idx_drivers_team ON drivers(team_id);
CREATE INDEX IF NOT EXISTS idx_engineers_team ON engineers(team_id);
CREATE INDEX IF NOT EXISTS idx_cars_team ON cars(team_id);
CREATE INDEX IF NOT EXISTS idx_cars_driver ON cars(driver_id);
CREATE INDEX IF NOT EXISTS idx_race_results_race ON race_results(race_id);
CREATE INDEX IF NOT EXISTS idx_race_results_driver ON race_results(driver_id);
CREATE INDEX IF NOT EXISTS idx_championship_team ON championship_standings(team_id);
CREATE INDEX IF NOT EXISTS idx_driver_substitutions_team ON driver_substitutions(team_id);

-- Tabela de Pontuação F1 (Pontos por posição)
CREATE TABLE IF NOT EXISTS f1_points_table (
    position INTEGER PRIMARY KEY,
    points INTEGER NOT NULL
);

-- Inserir tabela de pontuação F1 2024/2025
INSERT INTO f1_points_table (position, points) VALUES
(1, 25), (2, 18), (3, 15), (4, 12), (5, 10),
(6, 8), (7, 6), (8, 4), (9, 2), (10, 1)
ON CONFLICT (position) DO NOTHING;

