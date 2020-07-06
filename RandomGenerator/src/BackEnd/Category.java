package BackEnd;

import java.util.ArrayList;

public class Category {
    /*
    Fields
    - Keeps a reference to its parent tsvData object.
    - Keeps a list of all item objects that are associated with this category.
    - Keeps track of its own name and weight.
    Methods
    - Constructor
    - Getters & Setters
    - addItem()
    - toString() //this needs to be written
     */
    private TSVData tsvData;        //Reference
    private ArrayList<Item> items;  //Reference
    private ArrayList<Category> siblingCategories;
    private String name;
    private int weight;

    /// Constructor
    public Category(String name, TSVData tsvData){
        this.siblingCategories = new ArrayList<>(); //Need to be sorted
        System.out.println("Constructing Category: " + name);
        this.name = name;
        this.tsvData = tsvData;
        this.items = new ArrayList();
        weight = 10;
    }

    /// Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TSVData getCsvData() {
        return tsvData;
    }
    public void setCsvData(TSVData tsvData) {
        this.tsvData = tsvData;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public ArrayList<Category> getSiblingCategories() {
        return siblingCategories;
    }
    public void setSiblingCategories(ArrayList<Category> siblingCategories) {
        this.siblingCategories = siblingCategories;
    }

    ///Add Methods
    public void addItem(Item item){
        items.add(item);
    }
}

