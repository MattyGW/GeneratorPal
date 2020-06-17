package BackEnd;

import FrontEnd.Generator;
import javafx.scene.control.Button;

import java.util.ArrayList;

import static FrontEnd.InterfaceManager.setSize;

/*
Fields
- Keeps a reference to its parent csvData object.
- Keeps a list of all category objects this Item is associated with.
- Keeps track of its own name, rules and description.
Methods
- Constructor
- Getters & Setters
- addCategory()
- toString()
 */
public class Item {
    private ArrayList<Category>   itemCategory;
    private CSVData csvData;
    private String  name;
    private String  rules;
    private String  description;
    private Button itemButton;

    /// Constructor
    public Item(String name, String rules, String description, CSVData csvData) {
        System.out.println("Constructing Item: " + name);
        //Assigning Personal Traits
        this.name = name;
        this.rules = rules;
        this.description = description;
        //Assigning Parent Objects
        this.itemCategory = new ArrayList<>();
        this.csvData = csvData;
        //Display Section
        this.itemButton = new Button();
        itemButton.setMinSize(setSize("Gen").width,20);
        itemButton.setMaxSize(setSize("Gen").width,20);
    }

    ///Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRules() {
        return rules;
    }
    public void setRules(String rules) {
        this.rules = rules;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<Category> getItemCategory() {
        return itemCategory;
    }
    public void setItemCategory(ArrayList itemCategory) {
        this.itemCategory = itemCategory;
    }
    public CSVData getCSVData() {
        return csvData;
    }
    public void setCSVData(CSVData csvData) {
        this.csvData = csvData;
    }

    ///Add Methods
    public void addCategory(Category category){
        itemCategory.add(category);
    }

    ///Other Methods
    public void display(Generator generator){
        generator.getScrollPaneBody().getChildren().add(itemButton);
    }

    public String[] toString(Item item) {
        /// itemCategory toString ///
        String[] categoryNames;
        if (itemCategory.size()==1){
            categoryNames = new String[1];
            categoryNames[1] = itemCategory.get(1).getName();}
        else {
            categoryNames = new String[(itemCategory.size()*2)-1];
            int counter = 0;
            for (Category category : itemCategory){
                categoryNames[counter] = category.getName();
                if (counter == itemCategory.size()){
                    //If the last Category has been reached break
                    break;}
                else{
                    //Else add a comma and space then step to the next Catagory position
                    categoryNames[counter+1] = ", ";
                    counter += 2;
                }
            }
        }
        /// Output
        System.out.println("Item: " + name);
        System.out.println("From CSV: " + csvData.getName());
        System.out.println("Catagories: " + categoryNames);
        System.out.println("Rules: " + rules);
        System.out.println("Description: " + description);
        /// Return Value
        String[] returnValue = new String[6];
        returnValue[1] = ("Item: " + name);
        returnValue[3] = ("From CSV: " + csvData.getName());
        returnValue[4] = ("Catagories: " + categoryNames);
        returnValue[5] = ("Rules: " + rules);
        returnValue[6] = ("Description: " + description);
        return returnValue;
    }
}
