package client;

import client.GUI.GUIrun;
import client.Link.LinkFacade;
import client.Logic.LogicFacade;
import javafx.application.Application;
import javafx.stage.Stage;
import client.Acquaintance.IGUI;
import client.Acquaintance.ILink;
import client.Acquaintance.ILogic;

/**
 *
 * @author Robin
 */
public class Client extends Application {

    public static void main(String[] args) {
        IGUI gui = new GUIrun();
        ILogic logic = new LogicFacade();
        ILink link = new LinkFacade();
        logic.injectLink(link);
        gui.injectLogic(logic);
        gui.startApplication(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //this should just be empty - the method is just for making the program start here
    }
}
