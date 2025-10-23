package Data;

import Models.Engineer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineerDAO {

    private ConnectDB db;

    public EngineerDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertEngineer(Engineer engineer, int teamId) {
        String sql = "INSERT INTO engineers (team_id, name, age, wage, speciality, years_of_experience) " +
                     "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setString(2, engineer.getName());
            ps.setInt(3, engineer.getAge());
            ps.setDouble(4, engineer.getWage());
            ps.setString(5, engineer.speciality);
            ps.setInt(6, engineer.yearsOfExperience);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir engenheiro: " + e.getMessage());
        }
        return -1;
    }

    public Engineer getEngineerById(int id) {
        String sql = "SELECT * FROM engineers WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Engineer engineer = new Engineer(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getString("speciality"),
                        rs.getInt("years_of_experience")
                    );
                    engineer.setId(id);
                    engineer.setTeamId(rs.getInt("team_id"));
                    return engineer;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar engenheiro: " + e.getMessage());
        }
        return null;
    }

    public List<Engineer> getEngineersByTeam(int teamId) {
        List<Engineer> engineers = new ArrayList<>();
        String sql = "SELECT * FROM engineers WHERE team_id = ? ORDER BY speciality";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Engineer engineer = new Engineer(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getString("speciality"),
                        rs.getInt("years_of_experience")
                    );
                    engineer.setId(rs.getInt("id"));
                    engineer.setTeamId(teamId);
                    engineers.add(engineer);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar engenheiros da equipe: " + e.getMessage());
        }
        return engineers;
    }

    public List<Engineer> getAllEngineers() {
        List<Engineer> engineers = new ArrayList<>();
        String sql = "SELECT * FROM engineers ORDER BY name";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Engineer engineer = new Engineer(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getDouble("wage"),
                    rs.getString("speciality"),
                    rs.getInt("years_of_experience")
                );
                engineer.setId(rs.getInt("id"));
                engineer.setTeamId(rs.getInt("team_id"));
                engineers.add(engineer);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar engenheiros: " + e.getMessage());
        }
        return engineers;
    }

    public boolean updateEngineer(Engineer engineer) {
        String sql = "UPDATE engineers SET name = ?, age = ?, wage = ?, speciality = ?, " +
                     "years_of_experience = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, engineer.getName());
            ps.setInt(2, engineer.getAge());
            ps.setDouble(3, engineer.getWage());
            ps.setString(4, engineer.speciality);
            ps.setInt(5, engineer.yearsOfExperience);
            ps.setInt(6, engineer.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar engenheiro: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteEngineer(int id) {
        String sql = "DELETE FROM engineers WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar engenheiro: " + e.getMessage());
        }
        return false;
    }
}

