package credit;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.Remote;

public interface Credit extends Remote {

    String getType() throws Exception;

    void setType(String type) throws Exception;

    String getNumber() throws Exception;

    void setNumber(String number) throws Exception;

    String getExpDate() throws Exception;

    void setExpDate(String expDate) throws Exception;

    boolean isValid() throws Exception;

    boolean save(int accountId) throws Exception;

}
