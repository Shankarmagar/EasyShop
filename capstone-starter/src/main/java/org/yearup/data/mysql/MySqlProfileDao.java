package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.models.Profile;
import org.yearup.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao
{
    public MySqlProfileDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public Profile create(Profile profile)
    {
        String sql = "INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setString(4, profile.getPhone());
            ps.setString(5, profile.getEmail());
            ps.setString(6, profile.getAddress());
            ps.setString(7, profile.getCity());
            ps.setString(8, profile.getState());
            ps.setString(9, profile.getZip());

            ps.executeUpdate();

            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile getProfile(int userId) {
        String sql = "SELECT * " +
                "FROM profiles " +
                "WHERE user_id = ?;";
        try(Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet row = ps.executeQuery();
            Profile profile = new Profile();
            while(row.next())
            {
               int id = row.getInt("user_id");
               String first_name = row.getString("first_name");
               String last_name = row.getString("last_name");
               String phone = row.getString("phone");
               String email = row.getString("email");
               String address = row.getString("address");
               String city = row.getString("city");
               String  state = row.getString("state");
                String zip =  row.getString("zip");
              Profile p = new Profile(id, first_name, last_name, phone, email, address, city, state, zip);
              profile = p;
            }
            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile editProfile(int userId, Profile p) {
        String sql = "UPDATE profiles " +
                "SET first_name = ?, " +
                " last_name = ? ," +
                " phone = ? ," +
                " email = ? ," +
                " address = ? ," +
                " city = ?, " +
                " state = ?, " +
                " zip = ? " +
                " WHERE user_id = ? ;";
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setString(3, p.getPhone());
            statement.setString(4, p.getEmail());
            statement.setString(5, p.getAddress());
            statement.setString(6, p.getCity());
            statement.setString(7, p.getState());
            statement.setString(8, p.getZip());
            statement.setInt(9, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

}
