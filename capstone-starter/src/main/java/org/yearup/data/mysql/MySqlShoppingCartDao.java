package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.controllers.ProductsController;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    ProductDao productDao;
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        ShoppingCart MyCart = new ShoppingCart();

        String sql = "SELECT * FROM shopping_cart " +
                " WHERE user_id = ? ";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet row = statement.executeQuery();
            Map<Integer, ShoppingCartItem> res = getCartItem(row);
            MyCart.setItems(res);
            return MyCart;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, ShoppingCartItem> getCartItem(ResultSet row) throws SQLException {
        Map<Integer, ShoppingCartItem> res = new HashMap<>();
       while(row.next())
       {
           int productId = row.getInt("product_id");
           int quantity = row.getInt("quantity");

           Product p = productDao.getById(productId);
           ShoppingCartItem temp = new ShoppingCartItem();

           temp.setProduct(p);
           temp.setQuantity(quantity);

           res.put(productId, temp);

       }
        return res;
    }

}
