package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

public class LocalAccount implements Account {

    private String firstName;
    private String lastName;

    private Connection connection;

    public LocalAccount() {
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int save() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:customer_data_management.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO Account (first_name, last_name) "
                    + "VALUES('" + firstName + "', '" + lastName + "')");
            ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNT WHERE "
                    + "first_name='" + firstName + "' AND last_name='" + lastName + "'");
            return rs.getInt("id");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
