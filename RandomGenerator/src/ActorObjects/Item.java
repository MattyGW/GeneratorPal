package ActorObjects;

import java.util.ArrayList;

/*
Fields
- Keeps a reference to its parent tsvData object.
- Keeps a list of all tag objects this Item is associated with.
- Keeps track of its own name, rules and description.
Methods
- Constructor
- Getters & Setters
- addTag()
- toString()
 */
public class Item {
    private ArrayList<Tag>         itemTag;
    private TSVData tsvData;
    private String                      name;
    private String                      rules;
    private String                      description;

    /// Constructor
    public Item(String name, String rules, String description, TSVData tsvData) {
        System.out.println("Constructing Item: " + name);
        //Assigning Personal Traits
        this.name = name;
        this.rules = rules;
        this.description = description;
        //Assigning Parent Objects
        this.itemTag = new ArrayList<>();
        this.tsvData = tsvData;
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
    public ArrayList<Tag> getItemTag() {
        return itemTag;
    }
    public void setItemTag(ArrayList itemTag) {
        this.itemTag = itemTag;
    }
    public TSVData getTSVData() {
        return tsvData;
    }
    public void setTSVData(TSVData tsvData) {
        this.tsvData = tsvData;
    }

    ///Add Methods
    public void addTag(Tag tag){
        itemTag.add(tag);
    }

    public String[] toString(Item item) {
        /// itemTag toString ///
        String[] tagNames;
        if (itemTag.size()==1){
            tagNames = new String[1];
            tagNames[1] = itemTag.get(1).getName();}
        else {
            tagNames = new String[(itemTag.size()*2)-1];
            int counter = 0;
            for (Tag tag : itemTag){
                tagNames[counter] = tag.getName();
                if (counter == itemTag.size()){
                    //If the last Tag has been reached break
                    break;}
                else{
                    //Else add a comma and space then step to the next Catagory position
                    tagNames[counter+1] = ", ";
                    counter += 2;
                }
            }
        }
        /// Output
        System.out.println("Item: " + name);
        System.out.println("From TSV: " + tsvData.getName());
        System.out.println("Catagories: " + tagNames);
        System.out.println("Rules: " + rules);
        System.out.println("Description: " + description);
        /// Return Value
        String[] returnValue = new String[6];
        returnValue[1] = ("Item: " + name);
        returnValue[3] = ("From TSV: " + tsvData.getName());
        returnValue[4] = ("Catagories: " + tagNames);
        returnValue[5] = ("Rules: " + rules);
        returnValue[6] = ("Description: " + description);
        return returnValue;
    }

}
