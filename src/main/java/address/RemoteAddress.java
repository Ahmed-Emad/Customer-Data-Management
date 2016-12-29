package address;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class RemoteAddress extends UnicastRemoteObject implements Address {

    private String address;
    private String city;
    private String state;

    private Connection connection;

    public RemoteAddress() throws RemoteException {
    }

    @Override
    public String getAddress() throws RemoteException {
        return this.address;
    }

    @Override
    public void setAddress(String address) throws RemoteException {
        this.address = address;
    }

    @Override
    public String getCity() throws RemoteException {
        return this.city;
    }

    @Override
    public void setCity(String city) throws RemoteException {
        this.city = city;
    }

    @Override
    public String getState() throws RemoteException {
        return this.state;
    }

    @Override
    public void setState(String state) throws RemoteException {
        this.state = state;
    }

    @Override
    public boolean isValid() throws RemoteException {
        return true;
    }

    @Override
    public boolean save(int accountId) throws RemoteException {
        return true;
    }

}
