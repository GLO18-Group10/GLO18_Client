package client.Acquaintance;

/**
 *
 * @author Robin
 */
public interface ICustomer extends IUser{

    public String getBirthday();

    public void setBirthday(String birthday);

    public String getName();

    public void setName(String name);

    public String getAddress();

    public void setAddress(String address);

    public String getBankID();

    public String checkBankID(String ID);

}
