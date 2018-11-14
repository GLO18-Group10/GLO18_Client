/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import java.util.Random;

/**
 *
 * @author Nick
 */
public class Admin extends User {

    private LogicFacade logic;

    public Admin(String ID, LogicFacade logic) {
        super(ID, logic);
    }
    //Source code taken and adjusted from https://stackoverflow.com/questions/19743124/java-password-generator
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

}
