package factory;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import account.Account;
import account.LocalAccount;
import address.Address;
import address.LocalAddress;
import credit.Credit;
import credit.LocalCredit;

public class LocalCustomerFactory implements CustomerFactory {

    public LocalCustomerFactory() {
    }

    @Override
    public Account getAccount() {
        return new LocalAccount();
    }

    @Override
    public Address getAddress() throws Exception {
        return new LocalAddress();
    }

    @Override
    public Credit getCreditCard() throws Exception {
        return new LocalCredit();
    }

}