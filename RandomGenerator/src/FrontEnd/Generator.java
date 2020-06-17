package FrontEnd;

import Assets.AssetManager;
import BackEnd.CSVData;
import BackEnd.Category;
import BackEnd.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

import static FrontEnd.InterfaceManager.setSize;

public class Generator {
    //References
    AssetManager        assetManager;
    InterfaceManager    interfaceManager;
    //Parts of the Generator
    VBox                mainBody;
    Primary             stagePrimary;
    VBox                scrollPaneBody;
    //Data tracked by Generator
    String                      name;
    HashMap<String,CSVData>     selectedCSVDatas;
    HashMap<String,List<Object>> selectedCategories; //The list will be <Category, Integer>

    //Constructor
    public Generator(){}
    public Generator(InterfaceManager interfaceManager, String name, Primary stagePrimary) throws Exception {
        this.interfaceManager = interfaceManager;
        this.name = name;
        this.selectedCategories = new HashMap<>();
        this.selectedCSVDatas = new HashMap<>();
        this.stagePrimary = stagePrimary;
        VBox mainBody = new VBox();
        this.mainBody = mainBody;

        //mainBody Settings
        mainBody.setSpacing(0);
        mainBody.setPadding(new Insets(1, 1, 1, 1));
        mainBody.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.setMaxSize(setSize("Gen").width, setSize("Max").height);
        mainBody.setPrefSize(setSize("Gen").width, setSize("Pref").height);
        VBox.setVgrow(mainBody, Priority.ALWAYS);

        //title Settings
        Label title = new Label(name);
        title.setAlignment(Pos.CENTER);
        title.setBackground(new Background(new BackgroundFill(
                Color.rgb(245, 245, 245),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.getChildren().add(title);
        title.setMaxSize(setSize("Gen").width-2, 20);
        title.setMinSize(setSize("Gen").width-2, 20);

        //menuBar Settings
        MenuBar menuBar = new MenuBar();
        Menu OptionsMenu = new Menu("Options");
        MenuItem csvChangeItem = new MenuItem("Change CSVs");
        MenuItem categoriesChangeItem = new MenuItem("Change Categories");
        MenuItem generateItem = new MenuItem("Generate");
        MenuItem categoryWeightsItem = new MenuItem("Category Weights");
        Menu CSVsMenu = new Menu("CSVs");
        Menu CategoriesMenu = new Menu("Categories");
        menuBar.getMenus().add(OptionsMenu);
        OptionsMenu.getItems().add(csvChangeItem);
        OptionsMenu.getItems().add(categoriesChangeItem);
        OptionsMenu.getItems().add(generateItem);
        OptionsMenu.getItems().add(categoryWeightsItem);
        menuBar.getMenus().add(CSVsMenu);
        menuBar.getMenus().add(CategoriesMenu);

        csvChangeItem.setOnAction(e -> {
            try {
                changeCSVs();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        categoriesChangeItem.setOnAction(e -> {
            try {
                changeCatagories();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        generateItem.setOnAction(e -> {
            try {
                generate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        categoryWeightsItem.setOnAction(e -> {
            try {
                changeCategoryWeights();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //menuItem Events
//        newGeneratorMenuItem.setOnAction(e -> {
//            interfaceManager.createGenerator();
//        });

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        VBox scrollPaneBody = new VBox();
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneBody.setBackground(new Background((new BackgroundFill(
                Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY))));
        this.scrollPaneBody = scrollPaneBody;
        scrollPaneBody.setPrefHeight(setSize("Max").height);
        scrollPaneBody.setMaxWidth(setSize("Gen").width);
        scrollPane.setContent(scrollPaneBody);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //closeButton Settings

        //Assignment Section
        mainBody.getChildren().add(menuBar);
        mainBody.getChildren().add(scrollPane);
    }

    //Display Methods
    public void display(){
        stagePrimary.displayGenerator(mainBody);
    }

    public void hide(){
        stagePrimary.hideGenerator(mainBody);
    }

    //Selector Methods
    public void changeCSVs(){
        //prepare the arrays of strings to send to the selector window
        ArrayList<String> allOptions = new ArrayList<String>();
        for (CSVData csvData : interfaceManager.getAllCSVDatas()){
            allOptions.add(csvData.getName());
        }
        ArrayList<String> selectedOptions = new ArrayList<String>();
        for (CSVData csvData : selectedCSVDatas){
            selectedOptions.add(csvData.getName());
        }
        //call the selector window
        interfaceManager.selectScene.display("Changing CSVs",allOptions,selectedOptions);
        //update the list of selected CSVs
        if (interfaceManager.getStageSelect().getUpdateList()){
            selectedCSVDatas.clear();
            for (String csvDataName : interfaceManager.getStageSelect().getSelectedList()){
                for (CSVData csvData : interfaceManager.getAllCSVDatas()){
                if (csvDataName.equals(csvData.getName())){
                    selectedCSVDatas.add(csvData);
                }
                }
            }
        }
        for (Category category : selectedCategories){
            if (!(selectedCSVDatas.contains(category.getCsvData()))){
                selectedCategories.remove(category);
            }
        }
        trimCategoryWeight();
    }

    public void changeCatagories(){
        //prepare the arrays of strings to send to the selector window
        ArrayList<String> allOptions = new ArrayList<String>();
        for (CSVData csvData : selectedCSVDatas){
            for (Category category : csvData.getItemCategories()) {
                allOptions.add(category.getName());
            }
        }
        ArrayList<String> selectedOptions = new ArrayList<String>();
        for (Category category : selectedCategories){
            selectedOptions.add(category.getName());
        }
        //call the selector window
        interfaceManager.selectScene.display("Changing Categories",allOptions,selectedOptions);
        //update the list of selected Categories
        if (interfaceManager.getStageSelect().getUpdateList()){
            selectedCategories.clear();
            for (String categoryName : interfaceManager.getStageSelect().getSelectedList()) {
                for (CSVData csvData : selectedCSVDatas){
                    for (Category category : csvData.getItemCategories()) {
                        if (categoryName.equals(category.getName())) {
                            selectedCategories.add(category);
                        }
                    }
                }
            }
        }
        assignDefaultWeights();
        trimCategoryWeight();
    }

    //Category Weight Methods
    public void assignDefaultWeights(){
        //If the category hasn't been assigned a weight assign it a weight
        for (Category category : selectedCategories){
            //Go through all selected categories
            if (!(categoriesWeight.containsKey(category))){
                //If its already in the Hash Map skip it
                //Else add it
                categoriesWeight.put(category,50);
            }
        }
    }

    public void trimCategoryWeight(){
        //If a category isn't in selected categories it shouldn't be in categoriesNameWeight
        Iterator iterator = categoriesWeight.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)iterator.next();
            Category category = (Category) mapElement.getKey();
            if (!(selectedCategories.contains(category))){
                //If selected categories doesn't contain category remove that from categories weight
                categoriesWeight.remove(mapElement.getKey());
            }
        }
    }

    public void changeCategoryWeights(){
        interfaceManager.generatorOptions.display("Categories Weight",categoriesWeight);
        if (interfaceManager.generatorOptions.getUpdateList()){
            categoriesWeight = interfaceManager.generatorOptions.getCategoriesNewWeights();
        }
    }

    //Generation Methodz

    public void generate(){

    }

    public Item getRandomCategory(HashMap<String,Integer> mustCategories, HashMap<String,Integer> remainingCategories) throws Exception {
        //Step One (Setting up the total weight of the selected categories
        int currentWeight = 0;
        Iterator accumulator = remainingCategories.entrySet().iterator();
        while (accumulator.hasNext()){
            Map.Entry mapElement = (Map.Entry)accumulator.next();
            int integer = (int)mapElement.getValue();
            currentWeight += integer;
        }
        //Step Two (Generate a random number based on max weight)
        Random random = new Random();
        int randomnumber = random.nextInt(currentWeight);
        //Step Three (Select a random Category)
        Iterator counter = remainingCategories.entrySet().iterator();
        while (counter.hasNext()){
            Map.Entry mapElement = (Map.Entry)accumulator.next();
            int weight = (int)mapElement.getValue();
            String name = (String) mapElement.getKey();
            currentWeight =- weight;
            if (currentWeight > randomnumber){
                mustCategories.put(name, weight);
                return prepRemainingCategories(interfaceManager.getAllItems(), mustCategories);
            }
            else if (currentWeight < 0){
                throw new Exception("A category wasn't chosen for generating a category.");
            }
        }
        throw new Exception("This should ever be thrown.");
    }

    public Item prepRemainingCategories(ArrayList<Item> allItems, HashMap<String,Integer> mustCategories){
        HashMap<String,Integer> refractoredCateogryList = new HashMap<>();
        for (Item item : allItems){

        }
    }

    public void getRandomItem(){}

    //Getter & Setter
    public AssetManager getAssetManager() {
        return assetManager;
    }
    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    public InterfaceManager getInterfaceManager() {
        return interfaceManager;
    }
    public void setInterfaceManager(InterfaceManager interfaceManager) {
        this.interfaceManager = interfaceManager;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public VBox getMainBody() {
        return mainBody;
    }
    public void setMainBody(VBox mainBody) {
        this.mainBody = mainBody;
    }
    public Primary getStagePrimary() {
        return stagePrimary;
    }
    public void setStagePrimary(Primary stagePrimary) {
        this.stagePrimary = stagePrimary;
    }
    public VBox getScrollPaneBody() {
        return scrollPaneBody;
    }
    public void setScrollPaneBody(VBox scrollPaneBody) {
        this.scrollPaneBody = scrollPaneBody;
    }
    public ArrayList<Category> getSelectedCategories() {
        return selectedCategories;
    }
    public void setSelectedCategories(ArrayList<Category> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
    public ArrayList<CSVData> getSelectedCSVDatas() {
        return selectedCSVDatas;
    }
    public void setSelectedCSVDatas(ArrayList<CSVData> selectedCSVDatas) {
        this.selectedCSVDatas = selectedCSVDatas;
    }
}
