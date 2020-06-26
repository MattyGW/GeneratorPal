package FrontEnd;

import BackEnd.CSVData;
import BackEnd.Category;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class GeneratorOptions {
    ///References
    InterfaceManager interfaceManager;
    Stage stage;
    //Parts of the Selector
    VBox scrollPaneBody;
    //Data tracked the Selector
    Boolean updateList;
    HashMap<String,Integer> categoriesNewWeights;
        //Upon cancel or confirm this need to be reassigned

    //Constructor
    public GeneratorOptions(InterfaceManager interfaceManager){
        this.updateList = false;
        this.interfaceManager = interfaceManager;
        this.stage = new Stage();
        this.categoriesNewWeights = new HashMap<String, Integer>();
        // scrollPaneBody is initialized later

        stage.initModality(Modality.APPLICATION_MODAL);

        //mainBody Settings
        VBox mainBody = new VBox();
        mainBody.setPadding(new Insets(5,5,5,5));
        mainBody.setSpacing(0);
        mainBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.setMaxSize(250,400);
        mainBody.setMinSize(250,400);
//        mainBody.setAlignment(Pos.CENTER);

        //upperBody Settings
        VBox upperBody = new VBox();
        upperBody.setPadding(new Insets(3,2,3,2));
        upperBody.setSpacing(10);
        upperBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(30,30,30),
                CornerRadii.EMPTY, Insets.EMPTY)));
        upperBody.setMaxSize(240,355);
        upperBody.setMinSize(240,355);
        upperBody.setPrefSize(240,355);
        upperBody.setAlignment(Pos.CENTER);
        mainBody.getChildren().add(upperBody);

        //lowerBody Settings
        HBox lowerBody = new HBox();
        lowerBody.setPadding(new Insets(0,0,0,0));
        lowerBody.setSpacing(10);
        lowerBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        lowerBody.setMaxSize(240,40);
        lowerBody.setMinSize(240,40);
        lowerBody.setPrefSize(230,354);
        lowerBody.setAlignment(Pos.CENTER);
        mainBody.getChildren().add(lowerBody);

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        VBox scrollPaneBody = new VBox();
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneBody.setBackground(new Background((new BackgroundFill(
                Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY))));
        this.scrollPaneBody = scrollPaneBody;
        scrollPane.setMaxSize(230,349);
        scrollPane.setMinSize(230,349);
        scrollPane.setPrefSize(230,349);
        scrollPane.setContent(scrollPaneBody);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        upperBody.getChildren().add(scrollPane);

        //cancelButton Settings
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            try {
                cancel();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        lowerBody.getChildren().add(cancelButton);
        cancelButton.setMinSize(80,30);
        cancelButton.setMaxSize(80,30);
        cancelButton.setFont(Font.font("verdana", FontWeight.NORMAL, 10));

        //confirmButton Settings
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            try {
                confirm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        lowerBody.getChildren().add(confirmButton);
        confirmButton.setMinSize(80,30);
        confirmButton.setMaxSize(80,30);
        confirmButton.setFont(Font.font("verdana", FontWeight.NORMAL, 10));

        Scene PrimaryScene = new Scene(mainBody);
        stage.setScene(PrimaryScene);
        stage.setResizable(false);
    }

    //Display
    public void display(String title, HashMap<String,Integer> categoriesNameWeight, HashMap<String, Object> selectedCategories) {
        //Takes a HashMap to display
        //   - labeles with the text of the string part
        //   - Textfields with the text of the int part



        scrollPaneBody.getChildren().clear();

        Iterator iterator = categoriesNameWeight.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)iterator.next();
            String categoryName = (String)mapElement.getKey();
            int categoryWeight = (int)mapElement.getValue();

            HBox hBox = new HBox();

            Label label = new Label(categoryName);
            hBox.getChildren().add(label);

            TextField textField = new TextField();
            textField.setText(String.valueOf(categoryWeight));
            hBox.getChildren().add(textField);

            textField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")){
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });

            scrollPaneBody.getChildren().add(hBox);
        }

        stage.setTitle(title);
        stage.showAndWait();
    }

    //cancel
    public void cancel() throws Exception {
        //Sets Boolean update list to false
        this.updateList = false;
        //Clears selected list
        categoriesNewWeights.clear();
        stage.close();
    }

    //Button Methods
    public void confirm() throws Exception {
        //Sets Boolean update list to true
        this.updateList = true;
        //sets selected list to ArrayList<String> of selected labeles
        categoriesNewWeights.clear();
        for (Node node : scrollPaneBody.getChildren()){
            HBox hBox = (HBox) node;
            Label label = new Label();
            TextField textField = new TextField();
            int counter = 1;
            for (Node node1 : hBox.getChildren()){
                if (counter == 1) {
                    label = (Label) node1;
                }
                else {
                    textField = (TextField) node1;
                }
                counter ++;
            }
            categoriesNewWeights.put(label.getText(),Integer.parseInt(textField.getText()));
        }
        stage.close();
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

    public VBox getScrollPaneBody() {
        return scrollPaneBody;
    }

    public void setScrollPaneBody(VBox scrollPaneBody) {
        this.scrollPaneBody = scrollPaneBody;
    }

    public Boolean getUpdateList() {
        return updateList;
    }

    public void setUpdateList(Boolean updateList) {
        this.updateList = updateList;
    }

    public HashMap<String, Integer> getCategoriesNewWeights() {
        return categoriesNewWeights;
    }

    public void setCategoriesNewWeights(HashMap<String, Integer> categoriesNewWeights) {
        this.categoriesNewWeights = categoriesNewWeights;
    }
}
