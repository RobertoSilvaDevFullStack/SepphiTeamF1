package Data;

import Models.Race;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaceDAO {

    private ConnectDB db;

    public RaceDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertRace(Race race) {
        String sql = "INSERT INTO races (name, country, emoji, round_number, season_year) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, race.getName());
            ps.setString(2, race.getCountry());
            ps.setString(3, race.getEmoji());
            ps.setInt(4, race.getRoundNumber());
            ps.setInt(5, 2025);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir corrida: " + e.getMessage());
        }
        return -1;
    }

    public Race getRaceById(int id) {
        String sql = "SELECT * FROM races WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Race race = new Race(
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("emoji")
                    );
                    race.setId(id);
                    race.setRoundNumber(rs.getInt("round_number"));
                    return race;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corrida: " + e.getMessage());
        }
        return null;
    }

    public List<Race> getRacesByYear(int year) {
        List<Race> races = new ArrayList<>();
        String sql = "SELECT * FROM races WHERE season_year = ? ORDER BY round_number";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Race race = new Race(
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("emoji")
                    );
                    race.setId(rs.getInt("id"));
                    race.setRoundNumber(rs.getInt("round_number"));
                    races.add(race);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corridas: " + e.getMessage());
        }
        return races;
    }

    public List<Race> getUnsimulatedRaces() {
        List<Race> races = new ArrayList<>();
        String sql = "SELECT * FROM races WHERE is_simulated = FALSE AND season_year = 2025 ORDER BY round_number";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Race race = new Race(
                    rs.getString("name"),
                    rs.getString("country"),
                    rs.getString("emoji")
                );
                race.setId(rs.getInt("id"));
                race.setRoundNumber(rs.getInt("round_number"));
                races.add(race);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corridas não simuladas: " + e.getMessage());
        }
        return races;
    }

    public List<Race> getAllRaces() {
        List<Race> races = new ArrayList<>();
        String sql = "SELECT * FROM races WHERE season_year = 2025 ORDER BY round_number";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Race race = new Race(
                    rs.getString("name"),
                    rs.getString("country"),
                    rs.getString("emoji")
                );
                race.setId(rs.getInt("id"));
                race.setRoundNumber(rs.getInt("round_number"));
                races.add(race);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar corridas: " + e.getMessage());
        }
        return races;
    }

    public boolean markRaceAsSimulated(int raceId) {
        String sql = "UPDATE races SET is_simulated = TRUE, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raceId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao marcar corrida como simulada: " + e.getMessage());
        }
        return false;
    }

    public boolean updateRace(Race race) {
        String sql = "UPDATE races SET name = ?, country = ?, emoji = ?, round_number = ?, " +
                     "updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, race.getName());
            ps.setString(2, race.getCountry());
            ps.setString(3, race.getEmoji());
            ps.setInt(4, race.getRoundNumber());
            ps.setInt(5, race.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar corrida: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteRace(int id) {
        String sql = "DELETE FROM races WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar corrida: " + e.getMessage());
        }
        return false;
    }
}

