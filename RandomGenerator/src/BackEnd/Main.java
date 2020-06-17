package BackEnd;

import FrontEnd.InterfaceManager;
import FrontEnd.Primary;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void Primary(String[] args) {
        assert true; //Add a method for loading last saved work
        launch(args);
        assert true; //Add a method for Auto Saving Work
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting Program");
        InterfaceManager interfaceManager = new InterfaceManager();
        //TestBed
    }
}