package credit;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class RemoteCredit extends UnicastRemoteObject implements Credit {

    private static final String TYPE_REGEX = "[a-zA-Z]+(\\s[a-zA-Z]+)*";
    private static final String NUM_REGEX = "\\d{16}";
    private static final String EXP_REGEX = "\\d\\d/\\d\\d";
    private static Pattern typePattern;
    private static Pattern numberPattern;
    private static Pattern expPattern;

    private String type;
    private String number;
    private String expDate;

    private Connection connection;

    public RemoteCredit() throws RemoteException {
        typePattern = Pattern.compile(TYPE_REGEX);
        numberPattern = Pattern.compile(NUM_REGEX);
        expPattern = Pattern.compile(EXP_REGEX);
    }

    @Override
    public String getType() throws RemoteException {
        return this.type;
    }

    @Override
    public void setType(String type) throws RemoteException {
        this.type = type;
    }

    @Override
    public String getNumber() throws RemoteException {
        return this.number;
    }

    @Override
    public void setNumber(String number) throws RemoteException {
        this.number = number;
    }

    @Override
    public String getExpDate() throws RemoteException {
        return this.expDate;
    }

    @Override
    public void setExpDate(String expDate) throws RemoteException {
        this.expDate = expDate;
    }

    @Override
    public boolean isValid() throws RemoteException {
        return typePattern.matcher(type).matches() && numberPattern.matcher(number).matches()
                && expPattern.matcher(expDate).matches();
    }

    @Override
    public boolean save(int accountId) throws RemoteException {
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
