package address;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class LocalAddress implements Address {

    private static final String CITY_STATE_REGEX = "[a-zA-Z]+(\\s[a-zA-Z]+)*";
    private static final String ADDRESS_REGEX = "\\d{1,4}\\s([a-zA-Z]+(\\s[a-zA-Z]+)*)(,\\s([a-zA-Z]+(\\s[a-zA-Z]+)*))*";
    private static Pattern cityStatePattern;
    private static Pattern addressPattern;

    private String address;
    private String city;
    private String state;

    private Connection connection;

    public LocalAddress() {
        cityStatePattern = Pattern.compile(CITY_STATE_REGEX);
        addressPattern = Pattern.compile(ADDRESS_REGEX);
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean isValid() {
        return cityStatePattern.matcher(city).matches() && cityStatePattern.matcher(state).matches()
                && addressPattern.matcher(address).matches();
    }

    @Override
    public boolean save(int accountId) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:customer_data_management.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO Address (address, city, state, account_id) "
                    + "VALUES('" + address + "', '" + city + "', '" + state + "', " + accountId + ")");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
