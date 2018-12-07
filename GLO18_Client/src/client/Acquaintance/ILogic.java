/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Acquaintance;

/**
 *
 * @author Jeppe Enevold
 */
public interface ILogic {

    public void injectLink(ILink LinkLayer);

    void startConnection();

    void sendMessage(String message);

    public String receiveMessage();

    public String login(String ID, String password);

    public String toProtocol03(String name, String phoneNo, String address, String email);

    public ICustomer getCustomer();

    public IAdmin getAdmin();

    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password);

    public int getAccountBalance(String accountID);

    public String logout();

    public String toProtocol05(String senderID, String amount, String recieverID, String category, String text);

    public String getTransactionHistory(String accountID, String category);

    public String sendMailAutogenerated(String ID, String email, String name, String password);
    
    public String toProtocol09(String ID, boolean open);
    
    public String updatePassword(String ID, String oldPassword, String newPassword);
    
    public String checkPassword(String ID, String password);
    
    public String contactBank(String ID, String subject, String text);

    public String openBankAccount();

    public String changeTransactionCategory(String accountNo, String category, String dateToSend);

    public String lastLogin();
}
