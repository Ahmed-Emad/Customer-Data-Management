package account;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.Remote;

public interface Account extends Remote {

    String getFirstName() throws Exception;

    void setFirstName(String firstName) throws Exception;

    String getLastName() throws Exception;

    void setLastName(String lastName) throws Exception;

    boolean isValid() throws Exception;

    int save() throws Exception;

}
