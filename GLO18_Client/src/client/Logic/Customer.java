/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.ILogic;
import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class Customer extends User {

    private MessageParser messageParser = new MessageParser(logic);
    private String birthday;
    private String name;
    private String address;
    private ArrayList<String> bankIDs = new ArrayList<>();
    
    public Customer(String ID, ILogic logic) {
        super(ID, logic);
    }

    public String getBirthday() {
        //If birthday is null, get the birthday from the server
        if (birthday == null) {
            logic.sendMessage(messageParser.toProtocol01());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setBirthday(data[1]);
        }
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        //If name is null, get the name from the server
        if (name == null) {
            logic.sendMessage(messageParser.toProtocol01());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setName(data[0]);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        //If address is null, get the address from the server
        if (address == null) {
            logic.sendMessage(messageParser.toProtocol01());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            setAddress(data[3]);
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getBankID(){
        String bankID = "";
        if (bankIDs.isEmpty()) {
            logic.sendMessage(messageParser.toProtocol08());
            String data[] = messageParser.fromProtocol(logic.receiveMessage());
            System.out.println(data[0]);
            if (data[0].equalsIgnoreCase("error")) {
                return "No bankIDs";
            }
            for (String ID : data){
                bankIDs.add(ID + ";");
                
            
            }
        }
        for (int i = 0; i < bankIDs.size(); i++) {
            bankID = bankIDs.get(i);
        }
        return bankID;
    }
    
    public String checkBankID(String ID){
        if (bankIDs.isEmpty()) {
            getBankID();
        }
        for (String bankID : bankIDs) {
            if(bankID.equalsIgnoreCase(ID) == true){
                return "true";
            }
        }
        return "false";
    }
}
