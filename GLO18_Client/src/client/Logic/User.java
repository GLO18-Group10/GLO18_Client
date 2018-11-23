/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.ILogic;
import client.Acquaintance.IUser;

/**
 *
 * @author Nick
 */
public class User implements IUser {

    public ILogic logic;
    private MessageParser messageParser = new MessageParser(logic);
    private String ID;
    private String email;
    private String phoneNo;

    public User(String ID, ILogic logic) {
        this.ID = ID;
        this.logic = logic;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getEmail() {
        //If email is null, get the email from the server
        if (email == null) {
            logic.sendMessage(messageParser.toProtocol01(ID));
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setEmail(data[4]);
        }
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNo() {
        //If phone number is null, get the phone number from the server
        if (phoneNo == null) {
            logic.sendMessage(messageParser.toProtocol01(ID));
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setPhoneNo(data[2]);
        }
        return phoneNo;
    }

    @Override
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
