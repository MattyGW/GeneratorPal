package DisplayClasses;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Input {
    ///References
    InterfaceManager interfaceManager;
    Stage stage;
    //Fields
    Label descriptionLabel;
    TextField textField;
    Boolean updated;
    Object inputVariable;

    //Constructor
    public Input(InterfaceManager interfaceManager){
        this.interfaceManager = interfaceManager;
        this.stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        //mainBody Settings
        VBox mainBody = new VBox();
        mainBody.setPadding(new Insets(0,0,0,0));
        mainBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.setMaxSize(250,100);
        mainBody.setMinSize(250,100);

        //inputVBox
        VBox inputBox = new VBox();
        inputBox.setPadding(new Insets(5,10,5,10));
        inputBox.setSpacing(5);
        inputBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inputBox.setMaxSize(250,50);
        inputBox.setMinSize(250,50);
        mainBody.getChildren().add(inputBox);

        //descriptionLabel Settings
        this.descriptionLabel = new Label("Insert Input:");
        inputBox.getChildren().add(descriptionLabel);

        //textfield
        this.textField = new TextField();
        textField.setMaxSize(230,20);
        textField.setMinSize(230,20);
        inputBox.getChildren().add(textField);


        //buttonHBox
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(15,10,10,80));
        buttonBox.setSpacing(5);
        buttonBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(255,255,255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        buttonBox.setMaxSize(250,50);
        buttonBox.setMinSize(250,50);
        mainBody.getChildren().add(buttonBox);

        //closeButton Settings
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            try {
                close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonBox.getChildren().add(closeButton);
        closeButton.setMinSize(80,30);
        closeButton.setMaxSize(80,30);
        closeButton.setFont(Font.font("verdana", FontWeight.NORMAL, 10));

        //confirmButton Settings
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            try {
                confirm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonBox.getChildren().add(confirmButton);
        confirmButton.setMinSize(80,30);
        confirmButton.setMaxSize(80,30);
        confirmButton.setFont(Font.font("verdana", FontWeight.NORMAL, 10));

        Scene primaryScene = new Scene(mainBody);
        stage.setScene(primaryScene);
        stage.setResizable(false);
    }

    //Display
    public void display(String title, String textFieldLabel, Object inputVariable){
        //The call gives the input a variable and the inputScreen gets a input from the user of the same type.
        this.inputVariable = inputVariable;
        if (inputVariable.getClass() == Integer.class){
            textField.setText((String) inputVariable.toString());
            textField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                    if (!newValue.matches("\\d{0,3}")){
                        textField.setText(oldValue);
                    }
                }
            });
            assert true; //create a listener to force only numbers
        } else {
            textField.setText((String) inputVariable);
        }
        descriptionLabel.setText(textFieldLabel);
        stage.setTitle(title);
        stage.showAndWait();
    }

    //Buttons Methods
    public void close() throws Exception {
        updated = false;
        stage.close();
        textField.clear();
    }
    public void confirm() throws Exception {
        if (textField.getText().isBlank() ){
            interfaceManager.getErrorScene().display("Blank Input Error","The entry can't just be blank.");
        }
        else if(textField.getText().equals("0")) {
            interfaceManager.getErrorScene().display("Zero Input Error", "The entry can't be Zero.");
        } else {
            if (inputVariable.getClass().equals(Integer.class)) {
                inputVariable = Integer.parseInt(textField.getText());
            } else {
                inputVariable = textField.getText();
            }
            updated = true;
            stage.close();
        }
        textField.clear();
    }
    //Button Methods

    //Getters & Setters
}
