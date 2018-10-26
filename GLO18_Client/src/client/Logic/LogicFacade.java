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

    private static iLink Link;

    private Customer customer;
    private Admin admin;

    public void injectLink(iLink LinkLayer) {
        Link = LinkLayer;
    }

    @Override
    public void startConnection() {
        Link.startConnection();
    }

    @Override
    public void sendMessage(String message) {
        Link.sendMessage(message);
    }

    @Override
    public String receiveMessage() {
        return Link.receiveMessage();
    }

//    public String login(String ID, String password) {
//        String message = messageParser.toProtocol00(ID, password);
//        if (message.equalsIgnoreCase("true")) {
//            initializeUser(ID);
//        }
//        return message;
//    }

    @Override
    public String getName() {
        return customer.getName();
    }

    @Override
    public String getBirthday() {
        return customer.getBirthday();
    }

    @Override
    public String getPhoneNo() {
        return customer.getBirthday();
    }

    @Override
    public String getAddress() {
        return customer.getAddress();
    }

    @Override
    public String getEmail() {
        return customer.getEmail();
    }

    public void initializeUser(String ID) {
        if (ID.startsWith("A")) {
            admin = new Admin(ID, this);

        } else if (ID.startsWith("C")) {
            customer = new Customer(ID, this);
        }
    }
}
