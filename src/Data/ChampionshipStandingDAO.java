package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStandingDAO {

    private ConnectDB db;

    public ChampionshipStandingDAO(ConnectDB db) {
        this.db = db;
    }

    // Inserir ou atualizar posição no campeonato
    public int upsertStanding(int teamId, int totalPoints, int racesCompleted, int season) {
        // Primeiro, tentar buscar se já existe
        String selectSql = "SELECT id FROM championship_standings WHERE team_id = ? AND season_year = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectSql)) {
            ps.setInt(1, teamId);
            ps.setInt(2, season);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Já existe, fazer UPDATE
                    int existingId = rs.getInt("id");
                    String updateSql = "UPDATE championship_standings SET total_points = ?, races_completed = ? WHERE id = ?";
                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                        updatePs.setInt(1, totalPoints);
                        updatePs.setInt(2, racesCompleted);
                        updatePs.setInt(3, existingId);
                        updatePs.executeUpdate();
                        return existingId;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar posição no campeonato: " + e.getMessage());
        }

        // Não existe, fazer INSERT
        String insertSql = "INSERT INTO championship_standings (team_id, total_points, races_completed, season_year) " +
                           "VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, teamId);
            ps.setInt(2, totalPoints);
            ps.setInt(3, racesCompleted);
            ps.setInt(4, season);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir/atualizar posição no campeonato: " + e.getMessage());
        }
        return -1;
    }

    // Buscar posição de uma equipe no campeonato
    public Map<String, Object> getTeamStanding(int teamId, int season) {
        String sql = "SELECT cs.*, t.name as team_name " +
                     "FROM championship_standings cs " +
                     "JOIN teams t ON cs.team_id = t.id " +
                     "WHERE cs.team_id = ? AND cs.season_year = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setInt(2, season);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> standing = new HashMap<>();
                    standing.put("id", rs.getInt("id"));
                    standing.put("team_id", rs.getInt("team_id"));
                    standing.put("team_name", rs.getString("team_name"));
                    standing.put("total_points", rs.getInt("total_points"));
                    standing.put("races_completed", rs.getInt("races_completed"));
                    standing.put("season_year", rs.getInt("season_year"));
                    return standing;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar posição da equipe: " + e.getMessage());
        }
        return null;
    }

    // Buscar classificação geral do campeonato
    public List<Map<String, Object>> getChampionshipStandings(int season) {
        List<Map<String, Object>> standings = new ArrayList<>();
        String sql = "SELECT cs.*, t.name as team_name " +
                     "FROM championship_standings cs " +
                     "JOIN teams t ON cs.team_id = t.id " +
                     "WHERE cs.season_year = ? " +
                     "ORDER BY cs.total_points DESC, cs.races_completed DESC";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, season);

            try (ResultSet rs = ps.executeQuery()) {
                int position = 1;
                while (rs.next()) {
                    Map<String, Object> standing = new HashMap<>();
                    standing.put("position", position++);
                    standing.put("team_id", rs.getInt("team_id"));
                    standing.put("team_name", rs.getString("team_name"));
                    standing.put("total_points", rs.getInt("total_points"));
                    standing.put("races_completed", rs.getInt("races_completed"));
                    standings.add(standing);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar classificação do campeonato: " + e.getMessage());
        }
        return standings;
    }

    // Atualizar pontos de uma equipe
    public boolean updateTeamPoints(int teamId, int newPoints, int season) {
        String sql = "UPDATE championship_standings SET total_points = ?, updated_at = CURRENT_TIMESTAMP " +
                     "WHERE team_id = ? AND season_year = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newPoints);
            ps.setInt(2, teamId);
            ps.setInt(3, season);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pontos da equipe: " + e.getMessage());
        }
        return false;
    }

    // Incrementar corridas completadas
    public boolean incrementRacesCompleted(int teamId, int season) {
        String sql = "UPDATE championship_standings SET races_completed = races_completed + 1, " +
                     "updated_at = CURRENT_TIMESTAMP WHERE team_id = ? AND season_year = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setInt(2, season);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao incrementar corridas completadas: " + e.getMessage());
        }
        return false;
    }

    // Deletar classificação
    public boolean deleteStanding(int id) {
        String sql = "DELETE FROM championship_standings WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar classificação: " + e.getMessage());
        }
        return false;
    }
}

