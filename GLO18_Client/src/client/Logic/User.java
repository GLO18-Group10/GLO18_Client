/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

/**
 *
 * @author Nick
 */
public class User {

    public LogicFacade logic;
    public MessageParser messageParser = new MessageParser(logic);
    private String ID;
    private String email;
    private String phoneNo;

    public String getID() {
        return ID;
    }

    public String getEmail() {
        //If email is null, get the email from the server
        if (email == null) {
            logic.sendMessage(messageParser.toProtocol01());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setEmail(data[4]);
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        //If phone number is null, get the phone number from the server
        if (phoneNo == null) {
            logic.sendMessage(messageParser.toProtocol01());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setPhoneNo(data[2]);
        }
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User(String ID, LogicFacade logic) {
        this.ID = ID;
        this.logic = logic;
    }

}
