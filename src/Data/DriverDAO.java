package Data;

import Models.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    private ConnectDB db;

    public DriverDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertDriver(Driver driver, int teamId) {
        String sql = "INSERT INTO drivers (team_id, name, age, wage, car_number, handicap, points_of_season, is_reserve) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setString(2, driver.getName());
            ps.setInt(3, driver.getAge());
            ps.setDouble(4, driver.getWage());
            ps.setInt(5, driver.getCarNumber());
            ps.setInt(6, driver.getHandicap());
            ps.setInt(7, driver.getPointsOfSeason());
            ps.setBoolean(8, false);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir piloto: " + e.getMessage());
        }
        return -1;
    }

    public Driver getDriverById(int id) {
        String sql = "SELECT * FROM drivers WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("car_number"),
                        rs.getInt("handicap"),
                        rs.getInt("points_of_season"),
                        rs.getBoolean("is_reserve")
                    );
                    driver.setId(id);
                    driver.setTeamId(rs.getInt("team_id"));
                    return driver;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar piloto: " + e.getMessage());
        }
        return null;
    }

    public List<Driver> getDriversByTeam(int teamId) {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers WHERE team_id = ? ORDER BY car_number";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("car_number"),
                        rs.getInt("handicap"),
                        rs.getInt("points_of_season"),
                        rs.getBoolean("is_reserve")
                    );
                    driver.setId(rs.getInt("id"));
                    driver.setTeamId(teamId);
                    drivers.add(driver);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pilotos da equipe: " + e.getMessage());
        }
        return drivers;
    }

    public List<Driver> getReserveDrivers(int teamId) {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers WHERE team_id = ? AND is_reserve = TRUE ORDER BY name";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("car_number"),
                        rs.getInt("handicap"),
                        rs.getInt("points_of_season"),
                        rs.getBoolean("is_reserve")
                    );
                    driver.setId(rs.getInt("id"));
                    driver.setTeamId(teamId);
                    drivers.add(driver);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pilotos reservas: " + e.getMessage());
        }
        return drivers;
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers ORDER BY name";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Driver driver = new Driver(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getDouble("wage"),
                    rs.getInt("car_number"),
                    rs.getInt("handicap"),
                    rs.getInt("points_of_season"),
                    rs.getBoolean("is_reserve")
                );
                driver.setId(rs.getInt("id"));
                driver.setTeamId(rs.getInt("team_id"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar pilotos: " + e.getMessage());
        }
        return drivers;
    }

    public boolean updateDriver(Driver driver) {
        String sql = "UPDATE drivers SET name = ?, age = ?, wage = ?, car_number = ?, " +
                     "handicap = ?, points_of_season = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, driver.getName());
            ps.setInt(2, driver.getAge());
            ps.setDouble(3, driver.getWage());
            ps.setInt(4, driver.getCarNumber());
            ps.setInt(5, driver.getHandicap());
            ps.setInt(6, driver.getPointsOfSeason());
            ps.setInt(7, driver.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar piloto: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteDriver(int id) {
        String sql = "DELETE FROM drivers WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar piloto: " + e.getMessage());
        }
        return false;
    }

    public boolean updateDriverPoints(int driverId, int points) {
        String sql = "UPDATE drivers SET points_of_season = points_of_season + ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, points);
            ps.setInt(2, driverId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pontos do piloto: " + e.getMessage());
        }
        return false;
    }

    public boolean setAsReserve(int driverId, boolean isReserve) {
        String sql = "UPDATE drivers SET is_reserve = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, isReserve);
            ps.setInt(2, driverId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao marcar piloto como reserva: " + e.getMessage());
        }
        return false;
    }
}

