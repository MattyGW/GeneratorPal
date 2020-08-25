package GeneratorPal.Objects.DataObjects;

import GeneratorPal.Datasets.TagDataset;

import java.util.HashMap;

public class Tag {
    //References
    private TagGroup tagGroup;
    private TagDataset TagDataset;
    private HashMap<String, Field> defaultFields;
    //Data Fields
    private String name;
    private String description;
    private Integer defaultWeight;
    private HashMap<String, Item> activeItems;
    private HashMap<Field, Integer> defaultTagOnFieldInteractions;

    //Constructor
    public Tag(String name) {
        this.name = name;
    }

    //Getters and Setters
    public TagGroup getTagGroup() {
        return tagGroup;
    }
    public void setTagGroup(TagGroup tagGroup) {
        this.tagGroup = tagGroup;
    }
    public GeneratorPal.Datasets.TagDataset getTagDataset() {
        return TagDataset;
    }
    public void setTagDataset(GeneratorPal.Datasets.TagDataset tagDataset) {
        TagDataset = tagDataset;
    }
    public HashMap<String, Field> getDefaultFields() {
        return defaultFields;
    }
    public void setDefaultFields(HashMap<String, Field> defaultFields) {
        this.defaultFields = defaultFields;
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
    public Integer getDefaultWeight() {
        return defaultWeight;
    }
    public void setDefaultWeight(Integer defaultWeight) {
        this.defaultWeight = defaultWeight;
    }
    public HashMap<String, Item> getActiveItems() {
        return activeItems;
    }
    public void setActiveItems(HashMap<String, Item> activeItems) {
        this.activeItems = activeItems;
    }
}

