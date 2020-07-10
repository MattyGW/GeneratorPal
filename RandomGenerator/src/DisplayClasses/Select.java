package DisplayClasses;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Select {
    private InterfaceManager interfaceManager;
    private Stage stage;
    //References to parts of the selector
    private VBox scrollPaneBody;
    //Field required for selector methods
    private Boolean updateList;
    private HashMap<String,Object> availableOptions; //List of possible items
    private HashMap<String,Object> selectedList; //List of selected items

    protected Select(InterfaceManager interfaceManager){
        this.interfaceManager = interfaceManager;
        this.stage = new Stage();
        //Initialize required fields
        this.updateList = false;
        this.availableOptions = new HashMap<String, Object>();
        this.selectedList = new HashMap<String, Object>();

        //mainBody Settings
        VBox mainBody = new VBox();
        interfaceManager.formatMainBody(mainBody);

        //upperBody Settings
        VBox upperBody = new VBox();
        interfaceManager.formatSecondaryBody(upperBody);
        mainBody.getChildren().add(upperBody);

        //lowerBody Settings
        HBox lowerBody = new HBox();
        interfaceManager.formatSecondaryBody(lowerBody);
        mainBody.getChildren().add(lowerBody);

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        interfaceManager.formatScrollPane(scrollPane);
        VBox scrollPaneBody = new VBox();
        this.scrollPaneBody = scrollPaneBody;
        scrollPane.setContent(scrollPaneBody);
        upperBody.getChildren().add(scrollPane);

        //cancelButton Settings
        Button cancelButton = new Button("Cancel");
        interfaceManager.formatButton(cancelButton);
        lowerBody.getChildren().add(cancelButton);
        cancelButton.setOnAction(e -> {
            try {
                cancel();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //confirmButton Settings
        Button confirmButton = new Button("Confirm");
        interfaceManager.formatButton(confirmButton);
        confirmButton.setOnAction(e -> {
            try {
                confirm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        lowerBody.getChildren().add(confirmButton);

        Scene primaryScene = new Scene(mainBody);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(primaryScene);
        stage.setResizable(false);
    }

    //Display
    public void display(String title, HashMap<String,Object> availableOptions, HashMap<String,Object> selectedList) {
        System.out.println("Display Select Scene");
        this.selectedList = selectedList;
        this.availableOptions = availableOptions;
        //Create all the checkboxes based on availableOptions
        Iterator iterator = availableOptions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aOption = (Map.Entry)iterator.next();
            CheckBox checkBox = new CheckBox((String)aOption.getKey());
            scrollPaneBody.getChildren().add(checkBox);
            //Update selectedList & tick checkbox based on selectedOptions
            if (selectedList.containsKey((String) aOption.getKey())){
                checkBox.setSelected(true);
            }
        }
        //Update the stage title
        stage.setTitle(title);
        //Show and wait
        stage.showAndWait();
    }

    //Button Methods
    public void cancel() throws Exception {
        System.out.println("Cancel Select Scene");
        //Sets Boolean update list to false
        this.updateList = false;
//        this.selectedList = null;
//        this.availableOptions = null;
        scrollPaneBody.getChildren().clear();
        //Closes Stage
        stage.close();
    }

    public void confirm() throws Exception {
        System.out.println("Confirm Select Scene");
        //Sets Boolean update list to true
        this.updateList = true;
        //Rewrites the selected list basis on what is ticked.
        this.selectedList.clear();
        for (Node node : scrollPaneBody.getChildren()){
            CheckBox checkBox = (CheckBox) node;
            if (checkBox.isSelected()){
                selectedList.put(checkBox.getText(),
                        availableOptions.get(checkBox.getText()));
            }
        }
        //Closes Stage
//        this.selectedList = null;
//        this.availableOptions = null;
        scrollPaneBody.getChildren().clear();
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

    public HashMap<String, Object> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(HashMap<String, Object> selectedList) {
        this.selectedList = selectedList;
    }
}
