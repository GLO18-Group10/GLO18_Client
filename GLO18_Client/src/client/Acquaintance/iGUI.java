/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Acquaintance;

/**
 *
 * @author Jeppe Enevold
 */
public interface iGUI {
    public void injectLogic(iLogic LogicLayer);
    public void startApplication(String[] args);
}
