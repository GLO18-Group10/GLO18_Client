
package client.Acquaintance;

/**
 *
 * @author irus
 */

public interface IAdmin extends IUser {
    public String generatePassword();
    public String[] getCustomerId();
    public String openCustomerAccount(String ID);
    public String closeCustomerAccount(String ID);
}
