/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.ILogic;

/**
 *
 * @author Peterzxcvbnm
 */
public class MessageParser {

    ILogic logic;

    public MessageParser(ILogic logic) {
        this.logic = logic;
    }

    public String toProtocol00(String ID, String password) {
        return "00" + ";" + ID + ";" + password;
    }

    /**
     * Protocol 01: get customer info
     *
     * @param ID
     * @return 01
     */
    public String toProtocol01(String ID) {
        return "01;" + ID;
    }

    public String toProtocol02(String accountID, String customerID) {
        return "02;" + accountID + ";" + customerID;
    }

    public String toProtocol03(String name, String phonenumber, String address, String email, String ID) {
        return "03;" + name + ";" + phonenumber + ";" + address + ";" + email + ";" + ID;
    }

    public String toProtocol04(String ID) {
        return "04;" + ID;
    }

    public String toProtocol05(String senderID, String amount, String recieverID, String text, String customerID, String category) {
        return "05;" + senderID + ";" + amount + ";" + recieverID  + ";" + text + ";" + customerID + ";" + category;
    }

    public String toProtocol06(String ID, String customerID, String category) {
        return "06;" + ID + ";" + customerID + ";" + category;
    }

    public String toProtocol07(String ID, String name, String birthday, String phonenumber, String address, String email, String password, String adminID) {
        return ("07;" + ID + ";" + name + ";" + birthday + ";" + phonenumber + ";" + address + ";" + email + ";" + password + ";" + adminID);
    }

    public String toProtocol08(String ID) {
        return "08;" + ID;
    }

    public String toProtocol09(String ID, boolean open, String adminID) {
        return "09;" + "C" + ID + ";" + (open ? "1" : "0") + ";" + adminID;
    }

    public String toProtocol10(String adminID) {
        return "10;"+ adminID;
    }
    
    public String toProtocol11(String accountNo, String category, String date){
        return "11;" + accountNo + ";" + category + ";" + date;
    }

    public String toProtocol12(String ID, String customerID) {
        return "12;" + ID + ";" + customerID;
    }

    public String toProtocol13(String ID, String oldPassword, String newPassword) {
        return "13;" + ID + ";" + oldPassword + ";" + newPassword;
    }

    public String toProtocol14(String ID, String mailTo, String name, String password) {
        return "14;" + ID + ";" + mailTo + ";" + name + ";" + password;
    }

    public String toProtocol15(String ID, String subject, String message, String email) {
        return "15;" + ID + ";" + subject + ";" + message + ";" + email;
    }

    public String toProtocol16(String ID, String password) {
        return "16" + ";" + ID + ";" + password;
    }

    public String toProtocol18(String ID) {
        return "18;" + ID;
    }

    /**
     * Message to split the response from the server into an array
     *
     * @param message Message from the server
     * @return An array of the different parameters from the server
     */
    public String[] fromProtocol(String message) {
        //Split the received data into the different parts
        String[] data = message.split(";");
        return data;
    }
    public String toProtocol17(String ID){
        return "17;" + ID;
    }
}
