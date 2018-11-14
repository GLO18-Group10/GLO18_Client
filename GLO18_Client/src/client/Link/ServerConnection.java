/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Peterzxcvbnm
 * @since 2018-10-24
 * @version 1.0
 */
public class ServerConnection {
    
    private Socket socket;
    private SSLSocketFactory SSLSocketFactory;
    private SSLSocket SSLSocket;
    private Scanner scanner;

    public ServerConnection(String serverAddress, int serverPort) throws Exception {
        SSLSocketFactory factory =
        (SSLSocketFactory)SSLSocketFactory.getDefault();
         SSLSocket = (SSLSocket)factory.createSocket(serverAddress, serverPort);
         String[] supported = SSLSocket.getSupportedCipherSuites();
         SSLSocket.setEnabledCipherSuites(supported);
         SSLSocket.startHandshake();
        this.scanner = new Scanner(System.in);
    }

    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println(message);
        out.flush();
    }
    
    public String receiveMessage() throws IOException{
        BufferedReader in = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream()));
        return in.readLine();
    }
    
    public void endConnection(){
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("could not end connection");
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) throws Exception {
//        ServerConnection client = new ServerConnection(
//                "10.126.98.185",
//                Integer.parseInt("2345"));
//
//        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
//        
//        client.sendMessage("It works!!");
//        System.out.println(client.receiveMessage());
//
//        while (true);
//    }
}
