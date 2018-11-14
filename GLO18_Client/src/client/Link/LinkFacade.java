/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Link;

import client.Acquaintance.iLink;

/**
 *
 * @author Jeppe Enevold
 */
public class LinkFacade implements iLink {

    ServerConnection serverConnection;

    @Override
    public void startConnection() {
        try {
            serverConnection = new ServerConnection("10.126.33.125",
                    Integer.parseInt("2345"));
        }
        catch(Exception ioException){
            System.out.println("Connection error");
        }
    }

    @Override
    public void sendMessage(String message) {
        try{
            System.out.println(message);
        serverConnection.sendMessage(message);
        }
        catch(Exception e){
            System.out.println("Send message error - is the client connection running?");
            System.out.println(e.toString());
        }
 }

    @Override
    public String receiveMessage() {
        try{
            return serverConnection.receiveMessage();
        }
        catch(Exception e){
            return "error";  
        }
   }
    

}
