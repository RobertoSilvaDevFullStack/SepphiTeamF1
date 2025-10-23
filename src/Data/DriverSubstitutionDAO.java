package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverSubstitutionDAO {

    private ConnectDB db;

    public DriverSubstitutionDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertSubstitution(int teamId, int driverRemovedId, int driverAddedId, String reason, Integer raceId) {
        String sql = "INSERT INTO driver_substitutions (team_id, driver_removed_id, driver_added_id, reason, race_id) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setInt(2, driverRemovedId);
            ps.setInt(3, driverAddedId);
            ps.setString(4, reason);
            if (raceId != null) {
                ps.setInt(5, raceId);
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar substituição de piloto: " + e.getMessage());
        }
        return -1;
    }

    public List<Map<String, Object>> getTeamSubstitutions(int teamId) {
        List<Map<String, Object>> substitutions = new ArrayList<>();
        String sql = "SELECT ds.*, dr.name as driver_removed_name, da.name as driver_added_name " +
                     "FROM driver_substitutions ds " +
                     "JOIN drivers dr ON ds.driver_removed_id = dr.id " +
                     "JOIN drivers da ON ds.driver_added_id = da.id " +
                     "WHERE ds.team_id = ? ORDER BY ds.substitution_date DESC";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> sub = new HashMap<>();
                    sub.put("id", rs.getInt("id"));
                    sub.put("driver_removed_name", rs.getString("driver_removed_name"));
                    sub.put("driver_added_name", rs.getString("driver_added_name"));
                    sub.put("reason", rs.getString("reason"));
                    sub.put("substitution_date", rs.getTimestamp("substitution_date"));
                    substitutions.add(sub);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar substituições de pilotos: " + e.getMessage());
        }
        return substitutions;
    }

    public List<Map<String, Object>> getAllSubstitutions() {
        List<Map<String, Object>> substitutions = new ArrayList<>();
        String sql = "SELECT ds.*, t.name as team_name, dr.name as driver_removed_name, da.name as driver_added_name " +
                     "FROM driver_substitutions ds " +
                     "JOIN teams t ON ds.team_id = t.id " +
                     "JOIN drivers dr ON ds.driver_removed_id = dr.id " +
                     "JOIN drivers da ON ds.driver_added_id = da.id " +
                     "ORDER BY ds.substitution_date DESC";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> sub = new HashMap<>();
                sub.put("id", rs.getInt("id"));
                sub.put("team_name", rs.getString("team_name"));
                sub.put("driver_removed_name", rs.getString("driver_removed_name"));
                sub.put("driver_added_name", rs.getString("driver_added_name"));
                sub.put("reason", rs.getString("reason"));
                sub.put("substitution_date", rs.getTimestamp("substitution_date"));
                substitutions.add(sub);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar substituições: " + e.getMessage());
        }
        return substitutions;
    }

    public boolean deleteSubstitution(int id) {
        String sql = "DELETE FROM driver_substitutions WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar substituição: " + e.getMessage());
        }
        return false;
    }
}

