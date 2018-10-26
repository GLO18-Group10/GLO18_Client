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

    public String[] toProtocol01() {
        return messageParser.toProtocol01();
    }

    public String login(String ID, String password) {

        return messageParser.toProtocol00(ID, password);

    }

    @Override
    public String getName() {
   
    }

    @Override
    public String getBirthday() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhoneNo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAddress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
