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
    MessageParser messageParser = new MessageParser();
    private static User user;
    
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
    public String login(String ID, String password) {
        String message = messageParser.toProtocol00(ID, password);
        if (message.equalsIgnoreCase("true")) {
            initializeUser(ID);
            
        }
        
        return message;
    }
    
    
    public void initializeUser(String ID) {
        
        if (ID.startsWith("A")) {
            user = new Adminstrator(ID);
        
        } 
        else if (ID.startsWith("C")) {
            user = new Customer(ID);
        }
    }
}
