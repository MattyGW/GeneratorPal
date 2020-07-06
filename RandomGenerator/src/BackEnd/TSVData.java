package BackEnd;

import javax.lang.model.element.Element;
import java.io.*;
import java.util.*;

import static com.sun.javafx.fxml.expression.Expression.not;

public class TSVData {
    /*
    Fields
    - Keeps a list of all category objects associated with this TSVData.
    - Keeps a list of all Item objects associated with this TSVData.
    - Keeps track of its own name.
    Methods
    - Constructor
    - Getters & Setters
    - addCategory()
    - addItem()
    - toString() //This needs to be written
    - randomgenerator Methods
    - - getRandomItem()
    - - getRandomCategory()
    - - prepremainingCategories()
     */
    ///Personal Traits
    private String name;
    private ArrayList<Category> itemCategories;
    private ArrayList<Item> items;

    /// Constructor
    public TSVData(){}
    public TSVData(String name, File file) throws Exception {
        System.out.println("Constructing TSVData: " + name);
        this.name = name;
        this.itemCategories = new ArrayList<Category>();
        this.items = new ArrayList<Item>();
        //Data for category groups
        Integer groupCounter = 0;
        ArrayList<HashMap<String, Category>> allCategoryGroups = new ArrayList<>();
        ///Importing data from a tsv
        BufferedReader tsvReader;
        String currentRow = null;
        int counter = 1;
        try {
            tsvReader = new BufferedReader(new FileReader(file));
            currentRow = tsvReader.readLine();
        } catch (FileNotFoundException e) {
            throw new Exception("Method: loadItemTypesTSV \n - Unable to find file: \"" + file + "\"");}
        // Going through the open file
        while (currentRow != null) {
            //Other stuff
            String[] elements = currentRow.split("\t");
            int rowLength = elements.length;
            if (rowLength < 3){
                throw new Exception("The TSV file has less then 3 Columns it needs a name, rules and description roll");
            }
            else if (rowLength == 3){
                Category category = new Category("default", this);
                Item item = new Item(elements[0], elements[1], elements[2], this);
                category.addItem(item);
                item.addCategory(category);
                this.addItem(item);
                this.addCategory(category);
            }
            else { // happens when rowlength is > 3
                Item item = new Item(elements[0], elements[rowLength-2], elements[rowLength-1], this);
                this.addItem(item);
                for (int i = 1; i < rowLength-2; i++){
                    Category category = cautionedCategoryCreation(elements[i]);
                    System.out.println("There is " + allCategoryGroups.size() + " category groups, the row length is "+ rowLength);
                    if (allCategoryGroups.size() < (rowLength-3)){
                        System.out.println(" - Adding another category group.");
                        HashMap<String,Category> categoryGroup = new HashMap<>();
                        allCategoryGroups.add(categoryGroup);
                    }
                    //Stuff for category groups
                    allCategoryGroups.get(i-1).put(category.getName(),category);
                    category.addItem(item);
                    item.addCategory(category);
                    }
            }
            currentRow = tsvReader.readLine();
        }
        // Closing the Buffered Reader
        try {
            tsvReader.close();
        } catch (IOException e) {
            System.out.println("Unable to close file" + file);
            throw new Exception("Unable to close file" + file);
        }
        fillSiblingCategories(allCategoryGroups);
    }

    public void fillSiblingCategories(ArrayList<HashMap<String, Category>> allCategoryGroups){
        System.out.println("Filling Sibling Categories");
        System.out.println(allCategoryGroups);
        for (HashMap<String,Category> categoryGroup: allCategoryGroups){
            System.out.println(categoryGroup);
            Iterator iterator = categoryGroup.entrySet().iterator();
            ArrayList<Category> categoryGroupAsArray = new ArrayList<>();
            while (iterator.hasNext()) {
                Map.Entry categoryEntry = (Map.Entry) iterator.next();
                categoryGroupAsArray.add((Category)categoryEntry.getValue());
            }
            iterator = categoryGroup.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry categoryEntry = (Map.Entry) iterator.next();
                ((Category)categoryEntry.getValue()).setSiblingCategories((ArrayList) categoryGroupAsArray.clone());
                ((Category)categoryEntry.getValue()).getSiblingCategories().remove((Category)categoryEntry.getValue());
            }
        }
    }

    ///Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Category> getItemCategories() {
        return itemCategories;
    }
    public void setItemCategorys(ArrayList<Category> itemCategorys) {
        this.itemCategories = itemCategorys;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    ///Add Methods
    public void addItem(Item item){
        items.add(item);
    }
    public void addCategory(Category category){
        itemCategories.add(category);
    }

    ///Misc Methods
    public Category cautionedCategoryCreation(String categoryName){
        for (Category category : itemCategories){
            if (category.getName().equals(categoryName)){
                return category;
            }
        }
        Category category = new Category(categoryName,this);
        this.addCategory(category);
        return category;
    }

    ///Random Generator
    public Item getRandomItem() throws Exception {
        return getRandomCategory(this.getItems(), null, this.getItemCategories());
    }

    public Item getRandomCategory(ArrayList<Item> allItems, ArrayList<Category> mustCategories, ArrayList<Category> remainingCategories) throws Exception {
        //Step One (Setting up the total weight of the rePrimarying Categories)
        int currentWeight = 0;
        for (Category category: remainingCategories){
            currentWeight += category.getWeight();
        }
        //Step Two (Select create random number based on currentWeight)
        Random random = new Random();
        int randomNumber = random.nextInt(currentWeight);
        //Step Four (Select a random category)
        for (Category category : remainingCategories){
            currentWeight =- category.getWeight();
            if (currentWeight > randomNumber){
                mustCategories.add(category);
                return prepRaPrimaryingCategories(this.getItems(), mustCategories);
            }
            else if (currentWeight < 0 ){
                throw new Exception("A category wasn't chosen for generating a category.");
            }
        }
        throw new Exception("This should ever be thrown.");
    }

    public Item prepRaPrimaryingCategories(ArrayList<Item> allItems, ArrayList<Category> mustCategories) throws Exception {
        //Go through the items and remove items that don't have must categories
        ArrayList<Item> refactoredItemList = allItems;
        ArrayList<Category> refactoredCategoryList = new ArrayList<Category>();
        for (Item item : allItems) {
            for (Category category : mustCategories) {
                if (!(item.getItemCategory().contains(category))) {
                    refactoredItemList.remove(item);
                    break;
                }
            }
        }
        //Go through the items and create a new category list.
        for (Item item : refactoredItemList) {
            for (Category category : item.getItemCategory()) {
                if (!(refactoredCategoryList.contains(category))) {
                    refactoredCategoryList.add(category);
                }
            }
        }
        //Go through the category list removing the must categorys
        for (Category category : mustCategories) {
            if (refactoredCategoryList.contains(category)) {
                refactoredCategoryList.remove(category);
            }
        }
        //Check if refactored Category List is empty
        if (refactoredCategoryList.isEmpty()) {
            return generateRandomItem(refactoredItemList);
        }
        return getRandomCategory(refactoredItemList, mustCategories, refactoredCategoryList);
    }

    public Item generateRandomItem(ArrayList<Item> items) throws Exception {
        Random random = new Random();
        int randomNumber = random.nextInt(items.size());
        Iterator iterator = items.iterator();
        int currentIndex = 0;
        Element randomElement = null;
        while(iterator.hasNext()){
            randomElement = (Element) iterator.next();
            if (currentIndex == randomNumber){
                return (Item) randomElement;
            }
            currentIndex += 1;
        }
        throw new Exception("An item wasnt generated");
    }
}

