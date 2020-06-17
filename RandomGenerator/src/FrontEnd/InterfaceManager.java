package FrontEnd;

import Assets.AssetManager;
import BackEnd.CSVData;
import BackEnd.Category;
import BackEnd.Item;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class InterfaceManager {
    //Item Data
    HashMap<String,CSVData>     allCSVDatas;
    HashMap<String,Category>    allCategorys;
    HashMap<String,Item>        allItems;
    HashMap<String,Generator>   allGenerators;
    //Scene References
    Error           errorScene;
    Input           inputScene;
    Primary         primaryScene;
    Select          selectScene;
    GeneratorOptions generatorOptions;
    //Other References
    AssetManager    assetManager;

    //Constructor
    public InterfaceManager(){
        //Initialise Data Storage
        allCSVDatas = new HashMap<>();
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

    //Size Management Methods
    public static void formatButton(Button button){
        button.setMinSize(80,30);
        button.setMaxSize(80,30);
        button.setFont(Font.font("verdana", FontWeight.NORMAL,10));
    }

    public static void formatStage(Stage stage){
        stage.setMinSize(80,30);
        stage.setMaxSize(80,30);
        stage.setFont(Font.font("verdana", FontWeight.NORMAL,10));
    }

    public static int screenSize(String axis){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        switch (axis){
            case "width":   return (int) screenSize.width;
            case "height":  return (int) screenSize.height;
        }
        System.out.println("Error: screenSize: Incorrect Input: \"" + axis +"\"");
        //throw new Exception("Error: screenSize: Incorrect Input: \"" + axis +"\"");
        return 20;
    }
    public static Dimension setSize(String string){
        switch (string){
            case "Max": return new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            case "Min": return new Dimension(285,200);
            case "Pref": return new Dimension(285, 200);
            case "Gen": return new Dimension(267, 200);
        }
        System.out.println("Incorrect Input setSize");
        return new Dimension(500,500);
    }

    //Menu Methods
    public void createGenerator() throws Exception {
        inputScene.display("New Generator", "Insert Name: ", "String");
        if (!(inputScene.userInput.equals(false))){
            String input = (String) inputScene.getUserInput();
            Generator generator = new Generator(this, input,primaryScene);
            generator.display();
            allGenerators.put(generator.getName(),generator);
        }
    }

    public void inputCSV() throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(primaryScene.getStage());
        CSVData csvData = new CSVData(selectedFile.getName(),selectedFile);
        allCSVDatas.put(csvData.getName(),csvData);
        for (Category category: csvData.getItemCategories()){
            allCategorys.put(category.getName(),category);
        }
        for (Item item: csvData.getItems()){
            allItems.put(item.getName(),item);
        }
    }

    //Custom Getters & Setters
    public void getUserInput() {
    }

    //Field Getters & Setters
    public HashMap<String, CSVData> getAllCSVDatas() {
        return allCSVDatas;
    }
    public void setAllCSVDatas(HashMap<String, CSVData> allCSVDatas) {
        this.allCSVDatas = allCSVDatas;
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
}
