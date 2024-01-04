package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    ProductDao productDao;
    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;
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

            while (row.next())
            {
                ShoppingCartItem itemInCart = new ShoppingCartItem();
                Product p = productDao.getById(row.getInt("product_id"));
                int quantity = row.getInt("quantity");
                itemInCart.setQuantity(quantity);
                itemInCart.setProduct(p);

                MyCart.add(itemInCart);
                return MyCart;
            }

            return MyCart;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItem(int userId, int productId, int quantity) {

    }

    @Override
    public int setQuantity(int userId, int productId, int quantity) {
        return 0;
    }


}
