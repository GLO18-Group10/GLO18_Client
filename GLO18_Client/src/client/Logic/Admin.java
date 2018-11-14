/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.IAdmin;

/**
 *
 * @author Nick
 */
public class Admin extends User implements IAdmin {
// TODO FIX USER TO Private    
//    private MessageParser messeageParser = new MessageParser(logic);
    private LogicFacade logic;

    public Admin(String ID, LogicFacade logic) {
        super(ID, logic);
    }

    public String[] getCustomerId() {
        System.out.println("Admin initial");
    logic.sendMessage(messageParser.toProtocol10());
    String[] data = messageParser.fromProtocol(logic.receiveMessage());
        System.out.println("Admin: "+data[0]);
    return data;
}
}
