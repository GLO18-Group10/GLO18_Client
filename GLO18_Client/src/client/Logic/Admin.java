/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

import client.Acquaintance.ILogic;

/**
 *
 * @author Nick
 */
public class Admin extends User {

    private ILogic logic;

    public Admin(String ID, ILogic logic) {
        super(ID, logic);
    }

}
