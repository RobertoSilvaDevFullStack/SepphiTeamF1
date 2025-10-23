package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceResultDAO {

    private ConnectDB db;

    public RaceResultDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertRaceResult(int raceId, int driverId, int teamId, int finishPosition, int pointsEarned) {
        String sql = "INSERT INTO race_results (race_id, driver_id, team_id, finish_position, points_earned) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raceId);
            ps.setInt(2, driverId);
            ps.setInt(3, teamId);
            ps.setInt(4, finishPosition);
            ps.setInt(5, pointsEarned);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir resultado da corrida: " + e.getMessage());
        }
        return -1;
    }

    public List<Map<String, Object>> getRaceResults(int raceId) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT rr.*, d.name as driver_name, t.name as team_name " +
                     "FROM race_results rr " +
                     "JOIN drivers d ON rr.driver_id = d.id " +
                     "JOIN teams t ON rr.team_id = t.id " +
                     "WHERE rr.race_id = ? ORDER BY rr.finish_position";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raceId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("id", rs.getInt("id"));
                    result.put("driver_id", rs.getInt("driver_id"));
                    result.put("driver_name", rs.getString("driver_name"));
                    result.put("team_name", rs.getString("team_name"));
                    result.put("finish_position", rs.getInt("finish_position"));
                    result.put("points_earned", rs.getInt("points_earned"));
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar resultados da corrida: " + e.getMessage());
        }
        return results;
    }

    public List<Map<String, Object>> getDriverResults(int driverId, int season) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT rr.*, r.name as race_name, t.name as team_name " +
                     "FROM race_results rr " +
                     "JOIN races r ON rr.race_id = r.id " +
                     "JOIN teams t ON rr.team_id = t.id " +
                     "WHERE rr.driver_id = ? AND r.season_year = ? ORDER BY r.round_number";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, driverId);
            ps.setInt(2, season);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("race_name", rs.getString("race_name"));
                    result.put("finish_position", rs.getInt("finish_position"));
                    result.put("points_earned", rs.getInt("points_earned"));
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar resultados do piloto: " + e.getMessage());
        }
        return results;
    }

    public boolean deleteRaceResult(int id) {
        String sql = "DELETE FROM race_results WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar resultado da corrida: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteRaceAllResults(int raceId) {
        String sql = "DELETE FROM race_results WHERE race_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar resultados da corrida: " + e.getMessage());
        }
        return false;
    }
}

