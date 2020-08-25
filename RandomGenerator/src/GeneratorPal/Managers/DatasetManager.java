package GeneratorPal.Managers;

import GeneratorPal.Datasets.FieldDataset;
import GeneratorPal.Datasets.ItemDataset;
import GeneratorPal.Datasets.TagDataset;
import GeneratorPal.Datasets.TagGroupDataset;
import GeneratorPal.Objects.DataObjects.Field;
import GeneratorPal.Objects.DataObjects.Item;
import GeneratorPal.Objects.DataObjects.Tag;
import GeneratorPal.Objects.DataObjects.TagGroup;

import java.util.HashMap;
import java.util.Objects;

public class DatasetManager {
    //References
    private static DatasetManager uniqueInstance;
    //Data Fields
    HashMap<String, FieldDataset> fieldDatasets;
    HashMap<String, ItemDataset> itemDatasets;
    HashMap<String, TagDataset> tagDatasets;
    HashMap<String, TagGroupDataset> tagGroupDatasets;
    HashMap<String, Field> allFields;
    HashMap<String, Item> allItems;
    HashMap<String, Tag> allTags;
    HashMap<String, TagGroup> allTagGroups;
    HashMap<String, Object> unfinishedObjects;

    //Constructor
    private DatasetManager(){
        uniqueInstance = this;
    }
    public static DatasetManager getInstance(){
        return Objects.requireNonNullElseGet(uniqueInstance, DatasetManager::new);
    }

    //Other Methods
    public void linkObjects(Field field, String tagName, String interaction){
        if (!(allTags.containsKey(tagName))){
            //Create new tag
            Tag tag = new Tag(tagName);
            //Record the tag and that it is unfinished
            allTags.put(tag.getName(),tag);
            unfinishedObjects.put(tagName,(Object)tag);
        }
        //Get the tag
        Tag tag = allTags.get(tagName);
        //Update the objects with new information
        field.getDefaultTagAffects().put(allTags.get(tagName),Integer.valueOf(interaction));
        tag.getDefaultFields().put(field.getName(),field);
    }
    public void linkObjects(Tag tag, String tagGroupName){
        if (!(allTagGroups.containsKey(tagGroupName))){
            //Create new tagGroup
            TagGroup tagGroup = new TagGroup(tagGroupName);
            //Record the tagGroup and that it is unfinished
            allTagGroups.put(tagGroup.getName(),tagGroup);
            unfinishedObjects.put(tagGroupName,tagGroup);
        }
        //Get the tagGroup
        TagGroup tagGroup = allTagGroups.get(tagGroupName);
        //Update the objects with new information
        tag.setTagGroup(tagGroup);
        tagGroup.getDefaultTags().put(tag.getName(),tag);
    }
    public void linkObjects(TagGroup tagGroup, String tagName){
        if (!(allTags.containsKey(tagName))){
            //Create new tag
            Tag tag = new Tag(tagName);
            //Record the tag and that it is unfinished
            allTags.put(tag.getName(),tag);
            unfinishedObjects.put(tagName,tag);
        }
        //Get the tag
        Tag tag = allTags.get(tagName);
        //Update the objects with new information
        tagGroup.getDefaultTags().put(tag.getName(),tag);
        tag.setTagGroup(tagGroup);
    }
    public void linkObjects(Item item, String tagName){
        if (!(allTags.containsKey(tagName))){
            //Create new tag
            Tag tag = new Tag(tagName);
            //Record the tag and that it is unfinished
            allTags.put(tag.getName(),tag);
            unfinishedObjects.put(tagName,tag);
        }
        //Get the tag
        Tag tag = allTags.get(tagName);
        //Update the objects with new information
        item.getDefaultTags().put(tag.getName(),tag);
        tag.getActiveItems().put(item.getName(),item);
    }

    //Getters and Setters
    public HashMap<String, FieldDataset> getFieldDatasets() {
        return fieldDatasets;
    }
    public void setFieldDatasets(HashMap<String, FieldDataset> fieldDatasets) {
        this.fieldDatasets = fieldDatasets;
    }
    public HashMap<String, ItemDataset> getItemDatasets() {
        return itemDatasets;
    }
    public void setItemDatasets(HashMap<String, ItemDataset> itemDatasets) {
        this.itemDatasets = itemDatasets;
    }
    public HashMap<String, TagDataset> getTagDatasets() {
        return tagDatasets;
    }
    public void setTagDatasets(HashMap<String, TagDataset> tagDatasets) {
        this.tagDatasets = tagDatasets;
    }
    public HashMap<String, TagGroupDataset> getTagGroupDatasets() {
        return tagGroupDatasets;
    }
    public void setTagGroupDatasets(HashMap<String, TagGroupDataset> tagGroupDatasets) {
        this.tagGroupDatasets = tagGroupDatasets;
    }
    public HashMap<String, Field> getAllFields() {
        return allFields;
    }
    public void setAllFields(HashMap<String, Field> allFields) {
        this.allFields = allFields;
    }
    public HashMap<String, Item> getAllitems() {
        return allItems;
    }
    public void setAllitems(HashMap<String, Item> allItems) {
        this.allItems = allItems;
    }
    public HashMap<String, Tag> getAlltags() {
        return allTags;
    }
    public void setAlltags(HashMap<String, Tag> allTags) {
        this.allTags = allTags;
    }
    public HashMap<String, TagGroup> getAlltagGroups() {
        return allTagGroups;
    }
    public void setAlltagGroups(HashMap<String, TagGroup> allTagGroups) {
        this.allTagGroups = allTagGroups;
    }
    public HashMap<String, Item> getAllItems() {
        return allItems;
    }
    public void setAllItems(HashMap<String, Item> allItems) {
        this.allItems = allItems;
    }
    public HashMap<String, Tag> getAllTags() {
        return allTags;
    }
    public void setAllTags(HashMap<String, Tag> allTags) {
        this.allTags = allTags;
    }
    public HashMap<String, TagGroup> getAllTagGroups() {
        return allTagGroups;
    }
    public void setAllTagGroups(HashMap<String, TagGroup> allTagGroups) {
        this.allTagGroups = allTagGroups;
    }
    public HashMap<String, Object> getUnfinishedObjects() {
        return unfinishedObjects;
    }
    public void setUnfinishedObjects(HashMap<String, Object> unfinishedObjects) {
        this.unfinishedObjects = unfinishedObjects;
    }
}
