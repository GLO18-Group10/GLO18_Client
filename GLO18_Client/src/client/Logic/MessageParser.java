/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

/**
 *
 * @author Peterzxcvbnm
 */
public class MessageParser {

    LogicFacade logic;

    public MessageParser(LogicFacade logic) {
        this.logic = logic;
    }

    public String toProtocol00(String ID, String password) {
        String message = "00" + ";" + ID + ";" + password;
        logic.sendMessage(message);
        return fromProtocol00(logic.receiveMessage());

    }

    private String fromProtocol00(String message) {
        String data = message;
        return data;
    }

    public String[] toProtocol01() {
        logic.sendMessage("01");
        return fromProtocol(logic.receiveMessage());
    }

    private String[] fromProtocol(String message) {
        //Split the received data into the different parts
        String[] data = message.split(";");
        return data;
    }
}
