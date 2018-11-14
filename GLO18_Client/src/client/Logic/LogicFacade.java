/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.iLink;
import client.Acquaintance.iLogic;

/**
 *
 * @author Jeppe Enevold
 */
public class LogicFacade implements iLogic {

    private static User user;
    private static iLink link;
    private Customer customer;
    private Admin admin;

    private MessageParser messageParser = new MessageParser(this);

    @Override
    public void injectLink(iLink LinkLayer) {
        link = LinkLayer;
    }

    @Override
    public void startConnection() {
        link.startConnection();
    }

    @Override
    public void sendMessage(String message) {
        link.sendMessage(message);
    }

    @Override
    public String receiveMessage() {
        return link.receiveMessage();
    }

    @Override
    public String login(String ID, String password) {

        String message = messageParser.toProtocol00(ID, password);

        if (message.equalsIgnoreCase("true")) {
            initializeUser(ID);

        }

        return message;
    }

    @Override
    public String logout(){
        String message = messageParser.toProtocol18();
        
        if(message.equalsIgnoreCase("true")){
            admin = null;
            customer = null;
            link.endConnection();
            return "true";
        }else{
            return "false";
        }
        
    }
    
    @Override
    public int getAccountBalance(String accountID) {
        return messageParser.toProtocol02(accountID);
    }

    @Override
    public String getBankID(){
        return customer.getBankID();    
    }
    
    public String checkBankID(String bankID){
        return customer.checkBankID(bankID);
    
    } 

    public void initializeUser(String ID) {
        if (ID.startsWith("A")) {
            admin = new Admin(ID, this);

        } else if (ID.startsWith("C")) {
            customer = new Customer(ID, this);
        }
    }
    @Override
    public String toProtocol05(String senderID, String amount, String recieverID, String text){
        sendMessage(messageParser.toProtocol05(senderID, amount, recieverID, text));
        return receiveMessage();
    }
    
    @Override
    //This method could also be renamed to an appropriate name since the arcitecture has changed. For instance createCustomer(). This is preffered.
    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password) {
        sendMessage(messageParser.toProtocol07(ID, name, birthday, phonenumber, address, email, password));
        return receiveMessage();
    }

    
    @Override
    //This method will store the information for a specific customer
    public String toProtocol03(String name, String phoneNo, String address, String email){
        sendMessage(messageParser.toProtocol03(name, phoneNo, address, email));
        return receiveMessage();
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String getTransactionHistory(String accountID) {
        sendMessage(messageParser.toProtocol06(accountID));
        return receiveMessage();
    }
    
}
