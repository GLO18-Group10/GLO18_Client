package client;

import client.Acquaintance.iGUI;
import client.Acquaintance.iLink;
import client.Acquaintance.iLogic;
import client.GUI.GUIrun;
import client.Link.LinkFacade;
import client.Logic.LogicFacade;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Robin
 */
public class Client extends Application {

    public static void main(String[] args) {
        iGUI GUI = new GUIrun();
        iLogic Logic = new LogicFacade();
        iLink Link = new LinkFacade();
        Logic.injectLink(Link);
        GUI.injectLogic(Logic);
        GUI.startApplication(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //this should just be empty - the method is just for making the program start here
    }
}
