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
public class Admin extends User {

    private LogicFacade logic;

    public Admin(String ID, LogicFacade logic) {
        super(ID, logic);
    }

}
