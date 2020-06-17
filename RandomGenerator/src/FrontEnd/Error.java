package FrontEnd;

import Assets.AssetManager;
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

public class Error {
    ///References
    InterfaceManager interfaceManager;
    Stage stage;
    //Fields
    Label descriptionLabel;

    //Constructor
    public Error(InterfaceManager interfaceManager){
        this.interfaceManager = interfaceManager;
        this.stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        //mainBody Settings
        VBox mainBody = new VBox();
        mainBody.setPadding(new Insets(5,5,5,5));
        mainBody.setSpacing(10);
        mainBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.setMaxSize(250,100);
        mainBody.setMinSize(250,100);
        mainBody.setAlignment(Pos.CENTER);

        //upperBody Settings
        VBox upperBody = new VBox();
        upperBody.setPadding(new Insets(5,5,2,5));
        upperBody.setSpacing(10);
        upperBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        upperBody.setMaxSize(250,50);
        upperBody.setMinSize(250,50);
        upperBody.setAlignment(Pos.CENTER);
        mainBody.getChildren().add(upperBody);

        //lowerBody Settings
        VBox lowerBody = new VBox();
        lowerBody.setPadding(new Insets(2,5,5,5));
        lowerBody.setSpacing(10);
        lowerBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        lowerBody.setMaxSize(250,50);
        lowerBody.setMinSize(250,50);
        lowerBody.setAlignment(Pos.CENTER);
        mainBody.getChildren().add(lowerBody);

        //descriptionLabel Settings
        this.descriptionLabel = new Label("Error Description");
        upperBody.getChildren().add(descriptionLabel);
        descriptionLabel.wrapTextProperty().setValue(true);

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
    public void display(String title, String errorDescription){
        descriptionLabel.setText(errorDescription);
        stage.setTitle(title);
        stage.showAndWait();
    }

    //Close
    public void close() throws Exception {
        stage.close();
    }

    //Button Methods
    public void confirm() throws Exception {
        stage.close();
    }

    //Getters & Setters
}
