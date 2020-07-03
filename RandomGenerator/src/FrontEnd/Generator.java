package FrontEnd;

import Assets.AssetManager;
import BackEnd.CSVData;
import BackEnd.Category;
import BackEnd.Item;
import com.sun.javafx.collections.MappingChange;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Console;
import java.util.*;

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
    Integer                     count;
    Integer                     passCounter;
    HashMap<String,Object>      selectedCSVDatas;
    HashMap<String,Object>      selectedCategories;
    HashMap<String,Integer>     recordedCategoriesWeights;
    HashMap<Item,Integer>       displayedItems;

    //Constructor (Needs Checking)
    public Generator(){}
    public Generator(InterfaceManager interfaceManager, String name, Primary stagePrimary) throws Exception {
        this.interfaceManager = interfaceManager;
        this.name = name;
        this.count = 1;
        this.passCounter = 0;
        this.selectedCSVDatas = new HashMap<String,Object>();
        this.recordedCategoriesWeights = new HashMap<String,Integer>();
        this.selectedCategories = new HashMap<String,Object>();
        this.displayedItems = new HashMap<Item,Integer>();
        this.stagePrimary = stagePrimary;

        //mainBody Settings
        this.mainBody = new VBox();
        interfaceManager.formatMainBody(mainBody);
        VBox.setVgrow(mainBody, Priority.ALWAYS);

        //title Settings
        Label title = new Label(name);
        title.setAlignment(Pos.CENTER);
        title.setBackground(new Background(new BackgroundFill(
                Color.rgb(245, 245, 245),
                CornerRadii.EMPTY, Insets.EMPTY)));
        mainBody.getChildren().add(title);
        title.setPrefSize(248, 20);

        //menuBar Settings
        MenuBar menuBar = new MenuBar();

        Menu OptionsMenu = new Menu("Options");
        menuBar.getMenus().add(OptionsMenu);
        MenuItem generateItem = new MenuItem("Generate");
        OptionsMenu.getItems().add(generateItem);
        generateItem.setOnAction(e -> {
            try {
                setupGenerator();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem generateAmountItem = new MenuItem("Edit Amount Generated");
        OptionsMenu.getItems().add(generateAmountItem);
        generateAmountItem.setOnAction(e -> {
            try {
                changeCount();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Menu CSVsMenu = new Menu("CSVs");
        menuBar.getMenus().add(CSVsMenu);
        MenuItem csvSelectItem = new MenuItem("Edit Selected CSVs");
        CSVsMenu.getItems().add(csvSelectItem);
        csvSelectItem.setOnAction(e -> {
            try {
                selectCSVs();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Menu CategoriesMenu = new Menu("Edit Categories");
        menuBar.getMenus().add(CategoriesMenu);
        MenuItem categorySelectItem = new MenuItem("Edit Selected Categories");
        CategoriesMenu.getItems().add(categorySelectItem);
        categorySelectItem.setOnAction(e -> {
            try {
                selectCategories();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        MenuItem categoryWeightItem = new MenuItem("Edit Category Weights");
        CategoriesMenu.getItems().add(categoryWeightItem);
        categoryWeightItem.setOnAction(e -> {
            try {
                changeCategoryWeights();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        interfaceManager.formatScrollPane(scrollPane);
        VBox scrollPaneBody = new VBox();
        this.scrollPaneBody = scrollPaneBody;
        scrollPane.setContent(scrollPaneBody);

        //closeButton Settings

        //Assignment Section
        mainBody.getChildren().add(menuBar);
        mainBody.getChildren().add(scrollPane);
    }

    //Display Methods (Finished)
    public void display(){
        stagePrimary.displayGenerator(mainBody);
    }
    public void hide(){
        stagePrimary.hideGenerator(mainBody);
    }

    //Selector Methods
    public void selectCSVs(){
        //call the selector window
        interfaceManager.selectScene.display("Changing CSVs",(HashMap<String, Object>)interfaceManager.getAllCSVDatas(),selectedCSVDatas);
        //Automatically Deselect any category from a csv deselected
        if (interfaceManager.getSelectScene().getUpdateList()) {
            Iterator iterator = selectedCategories.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry aSelectedCategory = (Map.Entry) iterator.next();
                if (!(selectedCSVDatas.containsKey(((Category) (aSelectedCategory.getValue())).getCsvData().getName()))) {
                    selectedCategories.remove((String) aSelectedCategory.getKey());
                }
            }
        }
    }
    public void selectCategories(){
        //prepare the HashMaps to send to the selector window
        HashMap<String,Object> allOptions = new HashMap<>();
        Iterator iterator = selectedCSVDatas.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry csvEntry = (Map.Entry)iterator.next();
            for (Category category: ((CSVData) csvEntry.getValue()).getItemCategories()){
                allOptions.put(category.getName(), category);
            }
        }
        //call the selector window
        interfaceManager.selectScene.display("Selecting Categories",allOptions,selectedCategories);
        //update the list of selected Categories
        if (interfaceManager.getSelectScene().getUpdateList()){
            iterator = selectedCategories.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry selectedCategory = (Map.Entry)iterator.next();
                if (!(recordedCategoriesWeights.containsKey((String) selectedCategory.getKey()))){
                    recordedCategoriesWeights.put((String) selectedCategory.getKey(),60);
                }
            }
        }
    }

    //Category Weight Methods
    public void changeCategoryWeights(){
        interfaceManager.getGeneratorOptions().display("Change Categories Weight", recordedCategoriesWeights, selectedCategories);
        if (interfaceManager.getGeneratorOptions().getUpdateList()){
            recordedCategoriesWeights = interfaceManager.getGeneratorOptions().getCategoriesNewWeights();
        }
    }

    //Generater Methods
    public void setupGenerator() throws Exception {
        System.out.println("Setting Up Generator");
        //Clear the list of displayed items & scrollpane body
        displayedItems.clear();
        scrollPaneBody.getChildren().clear();
        //The HashMap remaining items if filtered down to a final list
        HashMap<String, Item> remainingItems = composeListOfRemainingItems(selectedCategories);
        //The Integer random is used to generate a random number
        Random random = new Random();
        //Select an item "count" times, based on selectedCategories and categoriesWeights
        for (int i = 0; i <= count-1; i++) {
            System.out.println(" - Generating Weighted Item Number : " + (i+1));
            try{
                Item item = filterRemainingItems((HashMap<String, Item>) remainingItems.clone(), (HashMap<String, Object>) selectedCategories.clone(), random);
                if (displayedItems.containsKey(item)) {
                    System.out.println(" - Increasing the count of " + item.getName() + " in displayItems.");
                    displayedItems.put(item, displayedItems.get(item) + 1);
                } else {
                    System.out.println(" - Adding the item " + item.getName() + " to displayItems.");
                    displayedItems.put(item, 1);
                }
            } catch (Exception e) {
                interfaceManager.getErrorScene().display("Zero Weight Error", "The weight of total selected categories is equal to zero");
                e.printStackTrace();
            }
            this.passCounter = 0;
        }
        System.out.println("Current state of display items" + displayedItems.keySet());
        displayGeneratedItems();
    }

    public Integer calculateCategoryWeights(HashMap<String, Object> categories ){
        System.out.println("  -> Calculating total weight of : " + categories.keySet().toString());
        int totalWeight = 0;
        Iterator iterator = categories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingCategory = (Map.Entry)iterator.next();
            totalWeight += (Integer) recordedCategoriesWeights.get((String)aRemainingCategory.getKey());
        }
        return totalWeight;
    }

    public HashMap<String,Item> composeListOfRemainingItems(HashMap<String,Object> remainingCategories){
        System.out.println(" - Composing list of remainingItems from remainingCategories");
        HashMap<String,Item> remainingItems = new HashMap<>();
        //Go through each category composing a list of items from their items.
        Iterator iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("  > Attempting to add \"" + ((Category)entry.getValue()).getItems() + "\" items from \"" + (String)entry.getKey() + "\"");
            for (Item item : (((Category) entry.getValue()).getItems())){
                remainingItems.put(item.getName(),item);
            }
        }
        return remainingItems;
    }

    public Category randomlySelectCategory(HashMap<String,Object> remainingCategories,
                                           Integer randomNumber) {
        System.out.println("  -> Randomly selecting category from remaining categories");
        Iterator iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry aRemainingCategory = (Map.Entry) iterator.next();
            randomNumber -= (Integer) recordedCategoriesWeights.get((String) aRemainingCategory.getKey());
            if (randomNumber < 0) {

                return (Category) aRemainingCategory.getValue();
            }
        }
        return null;
    }

    public Item filterRemainingItems(HashMap<String, Item> remainingItems,
                                     HashMap<String, Object> remainingCategories,
                                     Random random) throws Exception {
        int passCounter =+ 1;
        System.out.println("  >>> Filtering Remaining Items Pass : " + passCounter + "\n  -> Current number of items : " + remainingItems.size() + "\n  -> Current number of Categories : " + remainingCategories.size());
        //Generate a random number
        Integer randomNumber = random.nextInt(calculateCategoryWeights(remainingCategories));
        System.out.println("  -> Random Number For This Pass : " + randomNumber);
        //Selecting a category based on random number & remove it from remaining categories
        Category selectedCategory = randomlySelectCategory(remainingCategories,randomNumber);
        System.out.println("  -> The Category Selected is : " + selectedCategory.getName());
        remainingCategories.remove(selectedCategory.getName());
        //Go through all remaining items
        Iterator iterator;
        iterator = remainingItems.entrySet().iterator();
        ArrayList<String> itemsToBeFiltered = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry aRemainingItem = (Map.Entry) iterator.next();
            Item item = (Item) aRemainingItem.getValue();
            ////Go through the selected categories siblingCategories
            for (Category siblingCategory : selectedCategory.getSiblingCategories()) {
                //Remove items that contain a sibling category
                if (item.getItemCategory().contains(siblingCategory)) {
                    itemsToBeFiltered.add(item.getName());
                }
            }
        }
        for (Category siblingCategory : selectedCategory.getSiblingCategories()) {
            remainingCategories.remove(siblingCategory.getName());
        }
        for (String itemName:itemsToBeFiltered){
            remainingItems.remove(itemName);
        }
        System.out.println("  -> After pass number of items : " + remainingItems.size() + "\n  -> After Pass number of Categories : " + remainingCategories.size());
        //Checking to stop recursion
        if (remainingCategories.size() == 0) {
            return getRandomItem(remainingItems);
        } else {
            return filterRemainingItems(remainingItems, remainingCategories, random);
        }
    }

    public void displayGeneratedItems(){
        Iterator iterator = displayedItems.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry itemEntry = (Map.Entry) iterator.next();

            HBox itemSlot = new HBox();

            Label itemNameLabel = new Label(((Item) itemEntry.getKey()).getName());
            itemNameLabel.setMaxSize(interfaceManager.getScreenSize().width / 12, 20);

            Label itemCountLabel = new Label(("x" +((Integer) itemEntry.getValue()).toString()));
            itemCountLabel.setMaxSize(20, 20);

            Button itemButton = new Button();
            itemButton.setPrefSize(itemButton.getMaxWidth(), itemButton.getMaxWidth());
            itemButton.setMaxSize(20, 20);

            scrollPaneBody.getChildren().add(itemSlot);
            itemSlot.getChildren().add(itemNameLabel);
            itemSlot.getChildren().add(itemCountLabel);
            itemSlot.getChildren().add(itemButton);
        }
    }

    public Item getRandomItem(HashMap remainingItems){
        System.out.println("  >>> Generating Item From Filtered List");
        Random rand = new Random();
        int randomNumber = rand.nextInt(remainingItems.size());
        Iterator iterator = remainingItems.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingItem = (Map.Entry) iterator.next();
            randomNumber =- 1;
            if (randomNumber <= 0){
                System.out.println("  -> Chosen Item : " + aRemainingItem.getKey());
                return (Item) aRemainingItem.getValue();
            }
        }
        return null;
    }

    public void changeCount(){
        System.out.println("Attempting to change amount of items generated");
        interfaceManager.getInputScene().display("Change Amount Generated", "Amount", count);
        System.out.println(" - Count changed : " + interfaceManager.inputScene.updated);
        if ((interfaceManager.inputScene.updated.equals(true))){
            this.count = (Integer) interfaceManager.getInputScene().inputVariable;
            System.out.println(" - New count is : " + count);
        }
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public HashMap<String, Object> getSelectedCSVDatas() {
        return selectedCSVDatas;
    }

    public void setSelectedCSVDatas(HashMap<String, Object> selectedCSVDatas) {
        this.selectedCSVDatas = selectedCSVDatas;
    }

    public HashMap<String, Integer> getRecordedCategoriesWeights() {
        return recordedCategoriesWeights;
    }

    public void setRecordedCategoriesWeights(HashMap<String, Integer> recordedCategoriesWeights) {
        this.recordedCategoriesWeights = recordedCategoriesWeights;
    }

    public HashMap<String, Object> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(HashMap<String, Object> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
}
