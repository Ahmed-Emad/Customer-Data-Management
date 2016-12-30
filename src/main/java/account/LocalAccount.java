package account;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class LocalAccount implements Account {

    private static final String NAMES_REGEX = "[a-zA-Z]+";
    private static Pattern namesPattern;

    private String firstName;
    private String lastName;

    private Connection connection;

    public LocalAccount() {
        namesPattern = Pattern.compile(NAMES_REGEX);
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
        return namesPattern.matcher(firstName).matches() && namesPattern.matcher(lastName).matches();
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
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
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
