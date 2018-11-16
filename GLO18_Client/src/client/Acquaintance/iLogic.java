/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Acquaintance;

import client.Logic.Admin;
import client.Logic.Customer;

/**
 *
 * @author Jeppe Enevold
 */
public interface iLogic {

    public void injectLink(iLink LinkLayer);

    void startConnection();

    void sendMessage(String message);

    String receiveMessage();

    String getBankID();

    String login(String ID, String password);
    public String toProtocol03(String name, String phoneNo, String address, String email);
    Customer getCustomer();
    Admin getAdmin();
    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password);
    public int getAccountBalance(String accountID);

    public String logout();

    public String toProtocol05(String senderID, String amount, String recieverID, String text);

    public String getTransactionHistory(String accountID);

    public String sendMail(String ID, String email, String name, String password);

}
