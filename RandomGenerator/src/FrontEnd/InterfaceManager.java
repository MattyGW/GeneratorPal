package FrontEnd;

import Assets.AssetManager;
import BackEnd.TSVData;
import BackEnd.Category;
import BackEnd.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class InterfaceManager {
    //Item Data
    HashMap<String, Object>     allTSVDatas;
    HashMap<String, Category>   allCategorys;
    HashMap<String, Item>       allItems;
    HashMap<String,Generator>   allGenerators;
    //Scene References
    Error           errorScene;
    Input           inputScene;
    Primary         primaryScene;
    Select          selectScene;
    GeneratorOptions generatorOptions;
    //Other References
    AssetManager assetManager;
    private static InterfaceManager interfaceManager;

    //Constructor & Instance Method
    private InterfaceManager(){
        System.out.println("Constructing InterfaceManager");
        //Initialise Data Storage
        allTSVDatas = new HashMap<>();
        allCategorys = new HashMap<>();
        allItems = new HashMap<>();
        allGenerators = new HashMap<>();
        //Initialise Each Scene
        this.errorScene = new Error(this);
        this.inputScene = new Input(this);
        this.primaryScene = new Primary(this);
        this.selectScene = new Select(this);
        this.generatorOptions = new GeneratorOptions(this);
        this.assetManager = new AssetManager(this);
        //Display Primary Scene
        primaryScene.display();
    }
    public static InterfaceManager getInstance(){
        if (interfaceManager == null){
            interfaceManager = new InterfaceManager();
        }
        return interfaceManager;
    }

    //Formatting & Size Methods
    public void formatButton(Button button){
        button.setMinSize(80,30);
        button.setMaxSize(80,30);
        button.setPrefSize(80,30);
        button.setFont(Font.font("verdana", FontWeight.NORMAL,10));
    }
    public void formatStage(Stage stage){
        stage.setWidth((this.getScreenSize().width)/8); //Mess
        stage.setHeight((this.getScreenSize().height)/3); //Mess
        stage.setMaxHeight(this.getScreenSize().height);
        stage.setMinHeight((this.getScreenSize().height)/10);
        stage.setMaxWidth(this.getScreenSize().width);
        stage.setMinWidth((this.getScreenSize().width)/10);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }
    public void formatTextField(TextField textField){
        textField.setMaxSize(230,20);
        textField.setMinSize(230,20);
        textField.setPrefSize(230,20);
    }
    public void formatMainBody(Object object) {
        if (object.getClass().equals(VBox.class)) {
            VBox vBox = (VBox) object;
            vBox.setSpacing(0);
            vBox.setPadding(new javafx.geometry.Insets(1, 1, 1, 1));
            vBox.setBackground(new Background(new BackgroundFill(
                    Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.setPrefSize(250, 100);
            vBox.setAlignment(Pos.CENTER);
            VBox.setVgrow(vBox, Priority.ALWAYS);
        }
        if (object.getClass().equals(HBox.class)) {
            HBox hBox = (HBox) object;
            hBox.setSpacing(0);
            hBox.setPadding(new javafx.geometry.Insets(1, 1, 1, 1));
            hBox.setBackground(new Background(new BackgroundFill(
                    Color.rgb(80, 80, 80), CornerRadii.EMPTY, Insets.EMPTY)));
            hBox.setPrefSize(250, 100);
            VBox.setVgrow(hBox, Priority.ALWAYS);
        }
    }
    public void formatSecondaryBody(Object object) {
        if (object.getClass().equals(VBox.class)) {
            VBox vBox = (VBox) object;
            vBox.setSpacing(0);
            vBox.setPadding(new javafx.geometry.Insets(1, 1, 1, 1));
            vBox.setBackground(new Background(new BackgroundFill(
                    Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.setPrefWidth(98);
            VBox.setVgrow(vBox, Priority.ALWAYS);
        }
        if (object.getClass().equals(HBox.class)) {
            HBox hBox = (HBox) object;
            hBox.setSpacing(0);
            hBox.setPadding(new javafx.geometry.Insets(1, 1, 1, 1));
            hBox.setBackground(new Background(new BackgroundFill(
                    Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
            hBox.setPrefWidth(98);
            VBox.setVgrow(hBox, Priority.ALWAYS);
        }
    }
    public void formatScrollPane(ScrollPane scrollPane){
        scrollPane.hbarPolicyProperty().setValue(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setBackground(new Background((new BackgroundFill(
                Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY))));
        scrollPane.setMinSize(this.getScreenSize().width/12, this.getScreenSize().height/12);
        scrollPane.setMaxSize(this.getScreenSize().width, this.getScreenSize().height);
        scrollPane.setPrefSize(this.getScreenSize().width, this.getScreenSize().height);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

    public Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    //Menu Methods
    public void createGenerator() throws Exception {
        String generatorName = "";
        inputScene.display("New Generator", "Insert Name: ", generatorName);
        if (inputScene.updated.equals(true)){
            String input = (String) inputScene.inputVariable;
            Generator generator = new Generator(this, input,primaryScene);
            generator.display();
            allGenerators.put(generator.getName(),generator);
        }
    }

    public void inputTSV() throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(primaryScene.getStage());
        TSVData tsvData = new TSVData(selectedFile.getName(),selectedFile);
        allTSVDatas.put(tsvData.getName(),tsvData);
        for (Category category: tsvData.getItemCategories()){
            allCategorys.put(category.getName(),category);
        }
        for (Item item: tsvData.getItems()){
            allItems.put(item.getName(),item);
        }
    }

    //Custom Getters & Setters
    public void getUserInput() {
    }

    //Field Getters & Setters
    public HashMap<String, Object> getAllTSVDatas() {
        return allTSVDatas;
    }
    public void setAllTSVDatas(HashMap<String, Object> allTSVDatas) {
        this.allTSVDatas = allTSVDatas;
    }
    public HashMap<String, Category> getAllCategorys() {
        return allCategorys;
    }
    public void setAllCategorys(HashMap<String, Category> allCategorys) {
        this.allCategorys = allCategorys;
    }
    public HashMap<String, Item> getAllItems() {
        return allItems;
    }
    public void setAllItems(HashMap<String, Item> allItems) {
        this.allItems = allItems;
    }
    public HashMap<String, Generator> getAllGenerators() {
        return allGenerators;
    }
    public void setAllGenerators(HashMap<String, Generator> allGenerators) {
        this.allGenerators = allGenerators;
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }
    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    public Error getErrorScene() {
        return errorScene;
    }
    public void setErrorScene(Error errorScene) {
        this.errorScene = errorScene;
    }
    public Input getInputScene() {
        return inputScene;
    }
    public void setInputScene(Input inputScene) {
        this.inputScene = inputScene;
    }
    public Primary getPrimaryScene() {
        return primaryScene;
    }
    public void setPrimaryScene(Primary primaryScene) {
        this.primaryScene = primaryScene;
    }
    public Select getSelectScene() {
        return selectScene;
    }
    public void setSelectScene(Select selectScene) {
        this.selectScene = selectScene;
    }
    public GeneratorOptions getGeneratorOptions() {
        return generatorOptions;
    }
    public void setGeneratorOptions(GeneratorOptions generatorOptions) {
        this.generatorOptions = generatorOptions;
    }

    public static InterfaceManager getInterfaceManager() {
        return interfaceManager;
    }

    public static void setInterfaceManager(InterfaceManager interfaceManager) {
        InterfaceManager.interfaceManager = interfaceManager;
    }
}
