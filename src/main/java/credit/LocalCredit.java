package credit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

public class LocalCredit implements Credit {

    private String type;
    private String number;
    private String expDate;

    private Connection connection;

    public LocalCredit() {
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getExpDate() {
        return this.expDate;
    }

    @Override
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean save(int accountId) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:customer_data_management.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("INSERT INTO Credit (type, num, exp_date, account_id) "
                    + "VALUES('" + type + "', '" + number + "', '" + expDate + "', " + accountId + ")");
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
