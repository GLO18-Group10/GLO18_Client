package client;

import client.Acquaintance.iGUI;
import client.Acquaintance.iLink;
import client.Acquaintance.iLogic;
import client.GUI.GUIrun;
import client.Link.LinkFacade;
import client.Logic.LogicFacade;

/**
 *
 * @author Robin
 */
public class Client {

    public static void main(String[] args) {
        iGUI GUI = new GUIrun();
        iLogic Logic = new LogicFacade();
        iLink Link = new LinkFacade();
        Logic.injectLink(Link);
        GUI.injectLogic(Logic);
        Logic.startConnection();
        GUI.startApplication(args);
    }
}
