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
    HashMap<String,Item>        displayedItems;

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
        this.displayedItems = new HashMap<String,Item>();
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
                setupAndCallGeneratorWeightedItem(count);
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
    public void setupAndCallGeneratorWeightedItem(Integer count){
        System.out.println("Setting Up Generator");
        HashMap<String, Item>       remainingItems      = new HashMap<String, Item>();
        HashMap<String, Object>   chosenCategories    = new HashMap<String,Object>();
        displayedItems.clear();
        //Go through all selected categories composing remainItems
        System.out.println(" - Composing list of remainingItems from selected categories");
        Iterator iterator = selectedCategories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry hashMapItem = (Map.Entry)iterator.next();
            System.out.println("  > Attempting to add items from : " + (String)hashMapItem.getKey());
            for (Item item : (((Category) hashMapItem.getValue()).getItems())){
                remainingItems.put(item.getName(),item);
            }
        }
        //Call generateWeightedItem a number of times equal to count
        for(int i = 1; i >= count ; i++){
            System.out.println(" - Calling generateWeightedItem pass : " + i);
            Item item = generateWeightedItem(selectedCategories, remainingItems, chosenCategories);
            displayedItems.put(item.getName(),item);
        }
        iterator = displayedItems.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry displayitem = (Map.Entry)iterator.next();
            System.out.println(" - Displaying item : " + displayitem.getKey());
            ((Item) displayitem.getValue()).display(this);
        }
        passCounter = 0;
    }
    public Item generateWeightedItem(HashMap<String, Object> remainingCategories, HashMap<String, Item> remainingItems, HashMap<String,Object> chosenCategories){
        int passCounter =+ 1;
        System.out.println("  > Generating Weighted Item Pass : " + passCounter);
        ////SETTING UP A RANDOM NUMBER
        //Assign the values of remaining categories to a new HashMap
        //Traverse remainingCategories
        // - Tallying the totalWeights.
        //Generate a random number to max totalWeight.
        ////SELECTING A RANDOM CATEGORY
        //Traverse remainingCategories.
        // - Subtract each categories weight from total weight.
        // - - If (totalWeight becomes negative)
        // - - - Set selectedCategory = currentCategory
        // - - - Add the selectedCategory to chosenCategories
        ////FILTERING THE (remainingItems) & BUILDING (remainingCategories)
        //Empty remainingCategories
        //Traverse remainingItems
        // - For each of selectedCategories siblingCategories
        // - - If (item contains siblingCategory)
        // - - - remove the item.
        // - - - break
        // - - Else
        // - - - Add all the items categories to remainingCategories
        //For (chosenCategories)
        // - remove category from remaining categories
        ////CHECKING TO STOP RECURSION AND FOR ERROR
        //If (len(remaining categories) == 0)
        // - Item item = GenerateItem(remainingItems)
        // - If (len(item.categories) < len(chosenCategories))
        // - - Throw Error (Item Data From CSVs doesn't match, items are being generated
        //                      based on number of items)
        //Else
        // - generateWeightedItem(remainingCategories, remainingItems, chosenCategories)
        //Working out total weight of all selected categories
        int totalWeight = 0;
        Iterator iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingCategory = (Map.Entry)iterator.next();
            totalWeight += (Integer) recordedCategoriesWeights.get((String)aRemainingCategory.getKey());
        }
        //Generate a random number to max totalWeight.
        Random rand = new Random();
        Integer randomNumber = rand.nextInt(totalWeight);
        //Selecting a category based on random number
        Category selectedCategory = null;
        iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingCategory = (Map.Entry)iterator.next();
            randomNumber -= (Integer)recordedCategoriesWeights.get((String)aRemainingCategory.getKey());
            if (randomNumber < 0){
                selectedCategory = (Category)aRemainingCategory.getValue();
                chosenCategories.put(selectedCategory.getName(),selectedCategory);
                break;
            }
        }
        //Filter remainingItems and filter remainingCategories
        remainingCategories.clear();
        for (Category siblingCategory: selectedCategory.getSiblingCategories()){
            iterator = remainingItems.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry aRemainingItem = (Map.Entry) iterator.next();
                Item item = (Item) aRemainingItem.getValue();
                if (item.getItemCategory().contains(siblingCategory)) {
                    remainingItems.remove(item.getName());
                } else {
                    for (Category category : item.getItemCategory()) {
                        if ((selectedCategories.containsValue(category))) {
                            remainingCategories.put(category.getName(), category);
                        }
                    }
                }
            }
        }
        //Checking to stop recursion
        if (remainingCategories.size() == 0){
            return getRandomItem(remainingItems);
        }
        else {
            generateWeightedItem(remainingCategories, remainingItems, chosenCategories);
        }
        return null;
    }
    public Item getRandomItem(HashMap remainingItems){
        Random rand = new Random();
        int randomNumber = rand.nextInt(remainingItems.size());
        Iterator iterator = remainingItems.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingItem = (Map.Entry) iterator.next();
            randomNumber =- 1;
            if (randomNumber <= 0){
                return (Item) aRemainingItem.getValue();
            }
        }
        return null;
    }
    public void changeCount(){
        interfaceManager.getInputScene().display("Change Amount Generated", "Amount");
        if (interfaceManager.getInputScene().getUpdate()){
            this.count = (Integer) interfaceManager.getInputScene().getUserInput();
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

    public HashMap<String, Item> getDisplayedItems() {
        return displayedItems;
    }

    public void setDisplayedItems(HashMap<String, Item> displayedItems) {
        this.displayedItems = displayedItems;
    }
}
