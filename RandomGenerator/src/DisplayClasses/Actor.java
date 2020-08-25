package DisplayClasses;

import Assets.AssetManager;
import GeneratorPalOld.ItemDataset;
import GeneratorPalOld.Tag;
import GeneratorPalOld.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

public class Actor {
    //References
    AssetManager        assetManager;
    InterfaceManager    interfaceManager;
    //Parts of the Actor
    VBox                mainBody;
    Primary             stagePrimary;
    VBox                scrollPaneBody;
    //Data tracked by Actor
    String                      name;
    Integer                     count;
    Integer                     passCounter;
    HashMap<String,Object>      selectedTSVDatas;
    HashMap<String,Object>      selectedCategories;
    HashMap<String,Integer>     recordedCategoriesWeights;
    HashMap<Item,Integer>       displayedItems;

    //Constructor (Needs Checking)
    public Actor(){}
    public Actor(InterfaceManager interfaceManager, String name, Primary stagePrimary) throws Exception {
        this.interfaceManager = interfaceManager;
        this.name = name;
        this.count = 1;
        this.passCounter = 0;
        this.selectedTSVDatas = new HashMap<String,Object>();
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

        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);

        Menu editMenu = new Menu("Edit");
        menuBar.getMenus().add(editMenu);

        MenuItem saveItem = new MenuItem("Save Actor");
        fileMenu.getItems().add(saveItem);
        saveItem.setOnAction(e -> {
            try {
                assert true;
//                interfaceManager.saveGanerator(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem generateItem = new MenuItem("Generate Items");
        fileMenu.getItems().add(generateItem);
        generateItem.setOnAction(e -> {
            try {
                setupActor();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem generateAmountItem = new MenuItem("Edit Amount Generated");
        fileMenu.getItems().add(generateAmountItem);
        generateAmountItem.setOnAction(e -> {
            try {
                changeCount();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem tsvSelectItem = new MenuItem("Edit Selected TSV");
        editMenu.getItems().add(tsvSelectItem);
        tsvSelectItem.setOnAction(e -> {
            try {
                selectTSVs();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem tagSelectItem = new MenuItem("Edit Selected Categories");
        editMenu.getItems().add(tagSelectItem);
        tagSelectItem.setOnAction(e -> {
            try {
                selectCategories();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        MenuItem tagWeightItem = new MenuItem("Edit Selected Tag Weights");
        editMenu.getItems().add(tagWeightItem);
        tagWeightItem.setOnAction(e -> {
            try {
                changeTagWeights();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button generateButton = new Button("Generate");
        generateButton.setPrefSize(250,25);
        generateButton.setMaxSize(250,25);
        generateButton.setMinSize(250,25);

        //scrollPane Settings
        ScrollPane scrollPane = new ScrollPane();
        interfaceManager.formatScrollPane(scrollPane);
        VBox scrollPaneBody = new VBox();
        this.scrollPaneBody = scrollPaneBody;
        scrollPane.setContent(scrollPaneBody);

        //closeButton Settings

        //Assignment Section
        mainBody.getChildren().add(menuBar);
        mainBody.getChildren().add(generateButton);
        mainBody.getChildren().add(scrollPane);
    }

    //Display Methods (Finished)
    public void display(){
        stagePrimary.displayActor(mainBody);
    }
    public void hide(){
        stagePrimary.hideActor(mainBody);
    }

    //Selector Methods
    public void selectTSVs(){
        //call the selector window
        interfaceManager.selectScene.display("Changing TSVs",(HashMap<String, Object>)interfaceManager.getAllTSVDatas(),selectedTSVDatas);
        //Automatically Deselect any tag from a tsv deselected
        if (interfaceManager.getSelectScene().getUpdateList()) {
            Iterator iterator = selectedCategories.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry aSelectedTag = (Map.Entry) iterator.next();
                if (!(selectedTSVDatas.containsKey(((Tag) (aSelectedTag.getValue())).getCsvData().getName()))) {
                    selectedCategories.remove((String) aSelectedTag.getKey());
                }
            }
        }
    }
    public void selectCategories(){
        //prepare the HashMaps to send to the selector window
        HashMap<String,Object> allOptions = new HashMap<>();
        Iterator iterator = selectedTSVDatas.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry tsvEntry = (Map.Entry)iterator.next();
            for (Tag tag: ((ItemDataset) tsvEntry.getValue()).getItemCategories()){
                allOptions.put(tag.getName(), tag);
            }
        }
        //call the selector window
        interfaceManager.selectScene.display("Selecting Categories",allOptions,selectedCategories);
        //update the list of selected Categories
        if (interfaceManager.getSelectScene().getUpdateList()){
            iterator = selectedCategories.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry selectedTag = (Map.Entry)iterator.next();
                if (!(recordedCategoriesWeights.containsKey((String) selectedTag.getKey()))){
                    recordedCategoriesWeights.put((String) selectedTag.getKey(),60);
                }
            }
        }
    }

    //Tag Weight Methods
    public void changeTagWeights(){
        interfaceManager.getActorOptions().display("Change Categories Weight", recordedCategoriesWeights, selectedCategories);
        if (interfaceManager.getActorOptions().getUpdateList()){
            recordedCategoriesWeights = interfaceManager.getActorOptions().getCategoriesNewWeights();
        }
    }

    //Generater Methods
    public void setupActor() throws Exception {
        System.out.println("Setting Up Actor");
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

    public Integer calculateTagWeights(HashMap<String, Object> categories ){
        System.out.println("  -> Calculating total weight of : " + categories.keySet().toString());
        int totalWeight = 0;
        Iterator iterator = categories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry aRemainingTag = (Map.Entry)iterator.next();
            totalWeight += (Integer) recordedCategoriesWeights.get((String)aRemainingTag.getKey());
        }
        return totalWeight;
    }

    public HashMap<String,Item> composeListOfRemainingItems(HashMap<String,Object> remainingCategories){
        System.out.println(" - Composing list of remainingItems from remainingCategories");
        HashMap<String,Item> remainingItems = new HashMap<>();
        //Go through each tag composing a list of items from their items.
        Iterator iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("  > Attempting to add \"" + ((Tag)entry.getValue()).getItems() + "\" items from \"" + (String)entry.getKey() + "\"");
            for (Item item : (((Tag) entry.getValue()).getItems())){
                remainingItems.put(item.getName(),item);
            }
        }
        return remainingItems;
    }

    public Tag randomlySelectTag(HashMap<String,Object> remainingCategories,
                                           Integer randomNumber) {
        System.out.println("  -> Randomly selecting tag from remaining categories");
        Iterator iterator = remainingCategories.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry aRemainingTag = (Map.Entry) iterator.next();
            randomNumber -= (Integer) recordedCategoriesWeights.get((String) aRemainingTag.getKey());
            if (randomNumber < 0) {

                return (Tag) aRemainingTag.getValue();
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
        Integer randomNumber = random.nextInt(calculateTagWeights(remainingCategories));
        System.out.println("  -> Random Number For This Pass : " + randomNumber);
        //Selecting a tag based on random number & remove it from remaining categories
        Tag selectedTag = randomlySelectTag(remainingCategories,randomNumber);
        System.out.println("  -> The Tag Selected is : " + selectedTag.getName());
        remainingCategories.remove(selectedTag.getName());
        //Go through all remaining items
        Iterator iterator;
        iterator = remainingItems.entrySet().iterator();
        ArrayList<String> itemsToBeFiltered = new ArrayList();
        while (iterator.hasNext()) {
            Map.Entry aRemainingItem = (Map.Entry) iterator.next();
            Item item = (Item) aRemainingItem.getValue();
            ////Go through the selected categories tagGroup
            for (Tag siblingTag : selectedTag.getSiblingCategories()) {
                //Remove items that contain a sibling tag
                if (item.getItemTag().contains(siblingTag)) {
                    itemsToBeFiltered.add(item.getName());
                }
            }
        }
        for (Tag siblingTag : selectedTag.getSiblingCategories()) {
            remainingCategories.remove(siblingTag.getName());
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
            itemSlot.setSpacing(1);
            itemSlot.setPadding(new Insets(1,1,1,1));
            itemSlot.setBackground(new Background((new BackgroundFill(
                    Color.rgb(60,60,60),CornerRadii.EMPTY, Insets.EMPTY))));
            itemSlot.setId("itemActorDisplayBody");

            Label itemNameLabel = new Label(((Item) itemEntry.getKey()).getName());
            itemNameLabel.setPrefSize(140,20);
            itemNameLabel.setBackground(new Background((new BackgroundFill(
                    Color.rgb(220,220,220),CornerRadii.EMPTY, Insets.EMPTY))));
            itemNameLabel.setPadding(new Insets(0,2,0,2));

            Label itemCountLabel = new Label(("Count : " +((Integer) itemEntry.getValue()).toString()));
            itemCountLabel.setPrefSize(70,20);
            itemCountLabel.setBackground(new Background((new BackgroundFill(
                    Color.rgb(220,220,220),CornerRadii.EMPTY, Insets.EMPTY))));
            itemCountLabel.setPadding(new Insets(0,2,0,2));

            Button itemButton = new Button();
            itemButton.setPrefSize(itemButton.getMaxWidth(), itemButton.getMaxWidth());
            itemButton.setPrefSize(19, 19);
            itemButton.setMinSize(19, 19);
            itemButton.setMaxSize(19, 19);

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

    public HashMap<String, Object> getSelectedTSVDatas() {
        return selectedTSVDatas;
    }

    public void setSelectedTSVDatas(HashMap<String, Object> selectedTSVDatas) {
        this.selectedTSVDatas = selectedTSVDatas;
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
