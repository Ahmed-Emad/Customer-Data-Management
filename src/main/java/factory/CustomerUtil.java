package factory;

/**
 * Created by ahmadbarakat on 365 / 30 / 16.
 */
public class CustomerUtil {

    public static CustomerFactory getCustFactory(String mode) {
        if (mode.equals("remote")) {
            return new RemoteCustomerFactory();
        } else {
            return new LocalCustomerFactory();
        }
    }

}
