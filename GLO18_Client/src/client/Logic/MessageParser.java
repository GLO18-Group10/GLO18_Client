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
/**
 * Method to send 
     * @return The message from the server already split into an array
 */
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
