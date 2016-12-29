package main;

/**
 * Created by ahmadbarakat on 365 / 30 / 16.
 */

import java.rmi.Naming;

import account.RemoteAccount;
import address.RemoteAddress;
import credit.RemoteCredit;

public class Server {

    public static void main(String args[]) {
        try {
            RemoteAccount remoteAccount = new RemoteAccount();
            Naming.rebind("rmi://localhost:7575/customer-data-management/account", remoteAccount);
            RemoteAddress remoteAddress = new RemoteAddress();
            Naming.rebind("rmi://localhost:7575/customer-data-management/address", remoteAddress);
            RemoteCredit remoteCredit = new RemoteCredit();
            Naming.rebind("rmi://localhost:7575/customer-data-management/credit", remoteCredit);
            System.out.println("(y)");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
