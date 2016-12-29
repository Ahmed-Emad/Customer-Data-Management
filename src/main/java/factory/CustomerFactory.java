package factory;

/**
 * Created by ahmadbarakat on 364 / 29 / 16.
 */

import account.Account;
import address.Address;
import credit.Credit;

public interface CustomerFactory {

    Account getAccount() throws Exception;

    Address getAddress() throws Exception;

    Credit getCreditCard() throws Exception;

}
