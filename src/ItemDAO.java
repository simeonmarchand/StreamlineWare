import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    // Insert an item into the database
    public boolean addItem(Connection connection, Item item) {
        String query = "INSERT INTO items (name, category, description, unit_price, quantity_in_stock, minimum_stock_level) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getCategory());
            statement.setString(3, item.getDescription());
            statement.setBigDecimal(4, item.getUnitPrice());
            statement.setInt(5, item.getQuantityInStock());
            statement.setInt(6, item.getMinimumStockLevel());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Other CRUD methods can be similarly implemented here
}
