package DatabaseAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private DatabaseConnection databaseConnection;

    public SupplierDAO() throws SQLException {
        databaseConnection = DatabaseConnection.getSingleInstance();
    }

    public boolean addSupplier(Supplier supplier) {
   String sql = "INSERT INTO suppliers (id, name, telephone, description) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, supplier.getId());
            statement.setString(2, supplier.getName());
            statement.setString(3, supplier.getTelephone());
            statement.setString(4, supplier.getDescription());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error adding supplier: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE suppliers SET name = ?, telephone = ?, description = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getTelephone());
            statement.setString(3, supplier.getDescription());
            statement.setInt(4, supplier.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating supplier: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting supplier: " + ex.getMessage());
            return false;
        }
    }

    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String telephone = resultSet.getString("telephone");
                    String description = resultSet.getString("description");
                    return new Supplier(id, name, telephone, description);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error getting supplier by ID: " + ex.getMessage());
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String telephone = resultSet.getString("telephone");
                String description = resultSet.getString("description");
                suppliers.add(new Supplier(id, name, telephone, description));
            }
        } catch (SQLException ex) {
            System.out.println("Error getting all suppliers: " + ex.getMessage());
        }
        return suppliers;
    }
}
