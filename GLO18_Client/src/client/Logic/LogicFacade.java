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

    @Override
    public void injectLink(iLink LinkLayer) {
        Link = LinkLayer;
    }

    public void startConnection() {
        Link.startConnection();
    }

    public void sendMessage(String message) {
        Link.sendMessage(message);
    }

    public String receiveMessage() {
        return Link.receiveMessage();

    }
}
