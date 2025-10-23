package Data;

import Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private ConnectDB db;

    public CarDAO(ConnectDB db) {
        this.db = db;
    }

    public int insertCar(Car car, int teamId) {
        String sql = "INSERT INTO cars (team_id, model, horse_power, aerodynamic_coefficient, performance) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getHorsePower());
            ps.setDouble(4, car.getAerodynamicCoefficient());
            ps.setDouble(5, car.getPerformance());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir carro: " + e.getMessage());
        }
        return -1;
    }

    public Car getCarById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Car car = new Car(
                        rs.getString("model"),
                        rs.getInt("horse_power"),
                        rs.getDouble("aerodynamic_coefficient")
                    );
                    car.setId(id);
                    car.setTeamId(rs.getInt("team_id"));
                    car.setPerformance(rs.getDouble("performance"));
                    return car;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carro: " + e.getMessage());
        }
        return null;
    }

    public List<Car> getCarsByTeam(int teamId) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE team_id = ? ORDER BY id";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Car car = new Car(
                        rs.getString("model"),
                        rs.getInt("horse_power"),
                        rs.getDouble("aerodynamic_coefficient")
                    );
                    car.setId(rs.getInt("id"));
                    car.setTeamId(teamId);
                    car.setPerformance(rs.getDouble("performance"));
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carros da equipe: " + e.getMessage());
        }
        return cars;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars ORDER BY model";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(
                    rs.getString("model"),
                    rs.getInt("horse_power"),
                    rs.getDouble("aerodynamic_coefficient")
                );
                car.setId(rs.getInt("id"));
                car.setTeamId(rs.getInt("team_id"));
                car.setPerformance(rs.getDouble("performance"));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar carros: " + e.getMessage());
        }
        return cars;
    }

    public boolean updateCar(Car car) {
        String sql = "UPDATE cars SET model = ?, horse_power = ?, aerodynamic_coefficient = ?, " +
                     "performance = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getHorsePower());
            ps.setDouble(3, car.getAerodynamicCoefficient());
            ps.setDouble(4, car.getPerformance());
            ps.setInt(5, car.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar carro: " + e.getMessage());
        }
        return false;
    }

    public boolean updateCarPerformance(int carId, double newPerformance) {
        String sql = "UPDATE cars SET performance = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPerformance);
            ps.setInt(2, carId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar performance do carro: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar carro: " + e.getMessage());
        }
        return false;
    }

    public boolean assignCarToDriver(int carId, int driverId) {
        String sql = "UPDATE cars SET driver_id = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, driverId);
            ps.setInt(2, carId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao associar carro ao piloto: " + e.getMessage());
        }
        return false;
    }
}

