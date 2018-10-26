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
    private MessageParser messageParser = new MessageParser(this);

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

    @Override
    public String[] toProtocol01() {
        return messageParser.toProtocol01();
    }

    @Override
    public String[] getCustomerInfo() {
        //Check if the information is missing from the client
        if (Customer.getName().equals("")
                || Customer.getBirthday().equals("")
                || Customer.getPhoneNo().equals("")
                || Customer.getAddress().equals("")
                || Customer.getEmail().equals("")) {
            //Get the information from the server
            return toProtocol01();
        }
        //Else just return the data already saved in the client
        else{
            String[] data = new String[5];
            data[0] = Customer.getName();
            data[1] = Customer.getBirthday();
            data[2] = Customer.getPhoneNo();
            data[3] = Customer.getAddress();
            data[4] = Customer.getEmail();
            return data;
        }
    }
}
