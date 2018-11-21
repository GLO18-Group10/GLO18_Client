
package client.Acquaintance;

/**
 *
 * @author irus
 */

public interface IAdmin extends IUser {
    public String generatePassword();
    public String[] getCustomerId();
    public boolean openCustomerAccount(String ID);
    public boolean closeCustomerAccount(String ID);
}
