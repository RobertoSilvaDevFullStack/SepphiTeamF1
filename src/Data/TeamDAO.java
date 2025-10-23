package Data;

import Models.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private ConnectDB db;

    public TeamDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertTeam(Team team) {
        String sql = "INSERT INTO teams (name, citizenship) VALUES (?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, team.getName());
            ps.setString(2, team.getCitizenship());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir equipe: " + e.getMessage());
        }
        return -1;
    }

    public Team getTeamById(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Team team = new Team(rs.getString("name"), rs.getString("citizenship"));
                    team.setId(id);
                    return team;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar equipe: " + e.getMessage());
        }
        return null;
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams ORDER BY name";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Team team = new Team(rs.getString("name"), rs.getString("citizenship"));
                team.setId(rs.getInt("id"));
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar equipes: " + e.getMessage());
        }
        return teams;
    }

    public boolean updateTeam(Team team) {
        String sql = "UPDATE teams SET name = ?, citizenship = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, team.getName());
            ps.setString(2, team.getCitizenship());
            ps.setInt(3, team.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar equipe: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar equipe: " + e.getMessage());
        }
        return false;
    }
}

