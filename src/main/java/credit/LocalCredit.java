package credit;

import java.sql.Connection;

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
        return true;
    }

}
