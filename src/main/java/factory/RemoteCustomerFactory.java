package factory;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import java.rmi.Naming;

import account.Account;
import address.Address;
import credit.Credit;

public class RemoteCustomerFactory implements CustomerFactory {

    public RemoteCustomerFactory() {
    }

    @Override
    public Account getAccount() throws Exception {
        return (Account) Naming.lookup("rmi://localhost:7575/customer-data-management/account");
    }

    @Override
    public Address getAddress() throws Exception {
        return (Address) Naming.lookup("rmi://localhost:7575/customer-data-management/address");
    }

    @Override
    public Credit getCreditCard() throws Exception {
        return (Credit) Naming.lookup("rmi://localhost:7575/customer-data-management/credit");
    }
}
