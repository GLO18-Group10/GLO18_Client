/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.IAdmin;
import client.Acquaintance.ICustomer;
import client.Acquaintance.ILink;
import client.Acquaintance.ILogic;


/**
 *
 * @author Jeppe Enevold
 */
public class LogicFacade implements ILogic {


    private static ILink link;
    private ICustomer customer;
    private IAdmin admin;
    private MailHandler mailHandler = new MailHandler();

    private MessageParser messageParser = new MessageParser(this);

    @Override
    public void injectLink(ILink LinkLayer) {
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
            link.startConnection(); //the connection is started again to make it possible to log in again
            return "true";
        }else{
            return "false";
        }
        
    }
    
    @Override
    public int getAccountBalance(String accountID) {
        return messageParser.toProtocol02(accountID);
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
        sendMessage(messageParser.toProtocol03(name, phoneNo, address, email, customer.getID()));
        return receiveMessage();
    }

    @Override
    public ICustomer getCustomer() {
        return customer;
    }


    @Override
    public String getTransactionHistory(String accountID) {
        sendMessage(messageParser.toProtocol06(accountID));
        return receiveMessage();
    }

    @Override
    public IAdmin getAdmin(){
        return admin;
    }


    @Override
    public String sendMail(String ID, String email, String name, String password){
        return mailHandler.sendMail(ID, email, name, password);
    }

}
