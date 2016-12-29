package credit;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class RemoteCredit extends UnicastRemoteObject implements Credit {

    private String type;
    private String number;
    private String expDate;

    private Connection connection;

    public RemoteCredit() throws RemoteException {
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
        return true;
    }

    @Override
    public boolean save(int accountId) throws RemoteException {
        return true;
    }

}
