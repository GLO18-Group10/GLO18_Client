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
    private String ID;
    private String email;
    private String phoneNo;
    
    public User(String ID, LogicFacade logic) {
        this.ID = ID;
        this.logic = logic;
    }
    
}
