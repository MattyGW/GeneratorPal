package GeneratorPal.Objects.DataObjects;

import GeneratorPal.Datasets.ItemDataset;
import GeneratorPal.Objects.Actors.Actor;

import java.util.HashMap;

public class Item {
    //References
    private ItemDataset itemDataset;
    //Data Fields
    private String name;
    private String description;
    private String rules;
    private HashMap<String, Tag> defaultTags;
    private Actor actor; //If selected run a actor e.g. a chest(generate what is in it)

    //Constructor
    public Item(String name) {
        this.name = name;
    }

    //Getters and Setters
    public ItemDataset getItemDataset() {
        return itemDataset;
    }
    public void setItemDataset(ItemDataset itemDataset) {
        this.itemDataset = itemDataset;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRules() {
        return rules;
    }
    public void setRules(String rules) {
        this.rules = rules;
    }
    public HashMap<String, Tag> getDefaultTags() {
        return defaultTags;
    }
    public void setDefaultTags(HashMap<String, Tag> defaultTags) {
        this.defaultTags = defaultTags;
    }
}
