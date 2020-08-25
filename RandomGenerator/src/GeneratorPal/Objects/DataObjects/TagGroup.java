package GeneratorPal.Objects.DataObjects;

import GeneratorPal.Datasets.TagGroupDataset;
import GeneratorPal.Objects.DataObjects.Tag;

import java.util.HashMap;

public class TagGroup {
    //Data Fields
    private HashMap<String, Tag> defaultTags;
    private HashMap<String, Tag> requiredTagGroupss;

    //Constructor
    public TagGroup(String name){
        this.name = name;
    }

    //Getters & Setters
    public TagGroupDataset getTagGroupDataset() {
        return tagGroupDataset;
    }
    public void setTagGroupDataset(TagGroupDataset tagGroupDataset) {
        this.tagGroupDataset = tagGroupDataset;
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
    public HashMap<String, Tag> getDefaultTags() {
        return defaultTags;
    }
    public void setDefaultTags(HashMap<String, Tag> defaultTags) {
        this.defaultTags = defaultTags;
    }
}
