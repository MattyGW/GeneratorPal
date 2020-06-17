package FrontEnd;

import Assets.AssetManager;
import BackEnd.*;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

import static FrontEnd.InterfaceManager.setSize;

public class Primary {
    //References
    InterfaceManager interfaceManager;
    Stage stage;
    //Generator Location
    HBox scrollPaneBody;
    //Unknown Stuff
    ArrayList<Node> allNodes;

    //Constructor
    public Primary(InterfaceManager interfaceManager){
        //Setting References
        this.interfaceManager = interfaceManager;

        //mainBody
        VBox mainBody = new VBox();
        mainBody.setSpacing(1);
        mainBody.setPadding(new Insets(0,0,0,0));
        mainBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(40,40,40),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.setMaxSize(setSize("Max").width,setSize("Max").height);
        mainBody.setMinSize(setSize("Pref").width,setSize("Pref").height);
        VBox.setVgrow(mainBody, Priority.ALWAYS);

        //outerBody
        AnchorPane outerBody = new AnchorPane();
        outerBody.getChildren().add(mainBody);
        outerBody.setTopAnchor(mainBody, 0.0);
        outerBody.setLeftAnchor(mainBody, 0.0);
        outerBody.setRightAnchor(mainBody, 0.0);
        outerBody.setBottomAnchor(mainBody, 0.0);

        //menuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newGeneratorMenuItem = new MenuItem("New Generator");
        MenuItem newCSVDataMenuItem = new MenuItem("Import CSVData");
        Menu editMenu = new Menu("Edit");
        Menu viewMenu = new Menu("View");
        Menu testMenu = new Menu("Test");
        MenuItem inputMenuItem = new MenuItem("Input Window");
        MenuItem selectMenuItem = new MenuItem("Select Window");
        MenuItem errorMenuItem = new MenuItem("Error Window");
        menuBar.getMenus().add(fileMenu);
        fileMenu.getItems().add(newGeneratorMenuItem);
        fileMenu.getItems().add(newCSVDataMenuItem);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(viewMenu);
        menuBar.getMenus().add(testMenu);
        testMenu.getItems().add(inputMenuItem);
        testMenu.getItems().add(selectMenuItem);
        testMenu.getItems().add(errorMenuItem);

        //menuItem Events
        newGeneratorMenuItem.setOnAction(e -> {
            try {
                interfaceManager.createGenerator();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        newCSVDataMenuItem.setOnAction(e -> {
            try {
                interfaceManager.inputCSV();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        inputMenuItem.setOnAction(e -> {
            try {
                interfaceManager.inputScene.display("Test Title","Test Text Field Label: ","String");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
//        selectMenuItem.setOnAction(e -> {
//            try {
//                interfaceManager.selectScene.display();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
        errorMenuItem.setOnAction(e -> {
            try {
                interfaceManager.errorScene.display("Default Error", "This is the default error, I should loop around and not fit on one line. Hello I hope I work.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        HBox scrollPaneBody = new HBox();
//        scrollPaneBody.setSpacing(1);
//        scrollPaneBody.setPadding(new Insets(1,1,1,1));
        scrollPaneBody.setBackground(new Background((new BackgroundFill(
                Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY))));
        this.scrollPaneBody = scrollPaneBody;
        scrollPaneBody.setPrefHeight(setSize("Max").height);
        scrollPaneBody.setPrefWidth(setSize("Max").width);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(scrollPaneBody);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //Assignment Section
        mainBody.getChildren().add(menuBar);
        mainBody.getChildren().add(scrollPane);

        //stage Settings
        this.stage = new Stage();
        Scene PrimaryScene = new Scene(outerBody);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Variable Weighted Random Generator");
        stage.setScene(PrimaryScene);
        stage.setWidth(setSize("Pref").width);
        stage.setHeight(setSize("Pref").height);
        stage.setMaxWidth(setSize("Max").width);
        stage.setMinWidth(setSize("Min").width);
        stage.setMaxHeight(setSize("Max").height);
        stage.setMinHeight(setSize("Min").height);
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
    public void displayGenerator(VBox generatorBody){
        scrollPaneBody.getChildren().add(generatorBody);
    }
    public void hideGenerator(VBox generatorBody){
        scrollPaneBody.getChildren().remove(generatorBody);
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
