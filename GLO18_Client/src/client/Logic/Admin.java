/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.IAdmin;
import client.Acquaintance.ILogic;
import java.util.Random;

/**
 *
 * @author Nick
 */
public class Admin extends User implements IAdmin {
   private String account; 
   private MessageParser messageParser = new MessageParser(logic);

    public Admin(String ID, ILogic logic) {

        super(ID, logic);
    }

    //Source code taken and adjusted from https://stackoverflow.com/questions/19743124/java-password-generator
    @Override
    public String generatePassword() {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialSymbols = "!#¤%&/()=?@£$€{[]}";
        StringBuilder sb = new StringBuilder();
        int n = 10;
        String set = lower + lower.toUpperCase() + specialSymbols + digits; // characters to choose from

        for (int i = 0; i < n; i++) {
            int k = new Random().nextInt(set.length());
            sb.append(set.charAt(k));
        }
        return sb.toString();
    }

    @Override
    public String[] getCustomerId() {
        logic.sendMessage(messageParser.toProtocol10());
        String[] data = messageParser.fromProtocol(logic.receiveMessage());
        return data;
    }
    
    @Override
    public boolean openCustomerAccount(String ID) {
        logic.sendMessage(messageParser.toProtocol09(ID, true));
        String data[] = messageParser.fromProtocol(logic.receiveMessage());
        return data[0].equals("1");
    }
    
    @Override
    public boolean closeCustomerAccount(String ID) {
        logic.sendMessage(messageParser.toProtocol09(ID, false));
        String data[] = messageParser.fromProtocol(logic.receiveMessage());
        return data[0].equals("1");
    }
    
    
}

