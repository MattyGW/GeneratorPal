package DisplayClasses;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Primary {
    //References
    InterfaceManager interfaceManager;
    Stage stage;
    //Actor Location
    HBox scrollPaneBody;
    //Unknown Stuff
    ArrayList<Node> allNodes;

    //Constructor
    public Primary(InterfaceManager interfaceManager){
        //Setting References
        this.interfaceManager = interfaceManager;

        //mainBody
        VBox mainBody = new VBox();
        interfaceManager.formatMainBody(mainBody);
        mainBody.setPrefSize(interfaceManager.getScreenSize().width,interfaceManager.getScreenSize().height);

        //menuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newActorMenuItem = new MenuItem("New Actor");
        MenuItem newTSVDataMenuItem = new MenuItem("Import TSVData");
        Menu editMenu = new Menu("Edit");
        Menu viewMenu = new Menu("View");
        Menu testMenu = new Menu("Test");
        MenuItem inputMenuItem = new MenuItem("Input Window");
        MenuItem selectMenuItem = new MenuItem("Select Window");
        MenuItem errorMenuItem = new MenuItem("Error Window");
        menuBar.getMenus().add(fileMenu);
        fileMenu.getItems().add(newActorMenuItem);
        fileMenu.getItems().add(newTSVDataMenuItem);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(viewMenu);
        menuBar.getMenus().add(testMenu);
        testMenu.getItems().add(inputMenuItem);
        testMenu.getItems().add(selectMenuItem);
        testMenu.getItems().add(errorMenuItem);

        //menuItem Events
        newActorMenuItem.setOnAction(e -> {
            try {
                interfaceManager.createActor();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        newTSVDataMenuItem.setOnAction(e -> {
            try {
                interfaceManager.inputTSV();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
//        inputMenuItem.setOnAction(e -> {
//            interfaceManager.inputScene.display("Test Title","Test Text Field Label: ");});
//        errorMenuItem.setOnAction(e -> {
//            interfaceManager.errorScene.display("Default Error", "This is the default error, I should loop around and not fit on one line. Hello I hope I work.");});

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        interfaceManager.formatScrollPane(scrollPane);
        HBox scrollPaneBody = new HBox();
        this.scrollPaneBody = scrollPaneBody;
        scrollPane.hbarPolicyProperty().setValue(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(scrollPaneBody);

        //Assignment Section
        mainBody.getChildren().add(menuBar);
        mainBody.getChildren().add(scrollPane);

        //stage Settings
        this.stage = new Stage();
        interfaceManager.formatStage(stage);
        stage.setResizable(true);
        stage.setWidth((interfaceManager.getScreenSize().width)/2);
        stage.setHeight((interfaceManager.getScreenSize().width)/3);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Variable Weighted Random Actor");
        Scene primaryScene = new Scene(mainBody);
        //defaultStyleSheet
        //defaultStyleSheet
        //D:\Coding Projects\RandomActor\RandomActor\src\defaultStyleSheet
        stage.setScene(primaryScene);
        primaryScene.getStylesheets().add(Primary.class.getResource("defaultStyleSheet.css").toExternalForm());
    }

    //Display
    public void display(){
        stage.showAndWait();
    }

    //Close
    public void close() throws Exception {
        stage.close();
    }

    ///Button Methods
    public void displayActor(VBox actorBody){
        scrollPaneBody.getChildren().add(actorBody);
    }
    public void hideActor(VBox actorBody){
        scrollPaneBody.getChildren().remove(actorBody);
    }

    //Getters & Setters

    public InterfaceManager getInterfaceManager() {
        return interfaceManager;
    }

    public void setInterfaceManager(InterfaceManager interfaceManager) {
        this.interfaceManager = interfaceManager;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public HBox getScrollPaneBody() {
        return scrollPaneBody;
    }

    public void setScrollPaneBody(HBox scrollPaneBody) {
        this.scrollPaneBody = scrollPaneBody;
    }

    public ArrayList<Node> getAllNodes() {
        return allNodes;
    }

    public void setAllNodes(ArrayList<Node> allNodes) {
        this.allNodes = allNodes;
    }
}
