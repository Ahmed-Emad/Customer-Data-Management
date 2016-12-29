package address;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.Remote;

public interface Address extends Remote {

    String getAddress() throws Exception;

    void setAddress(String address) throws Exception;

    String getCity() throws Exception;

    void setCity(String city) throws Exception;

    String getState() throws Exception;

    void setState(String state) throws Exception;

    boolean isValid() throws Exception;

    boolean save(int accountId) throws Exception;

}
