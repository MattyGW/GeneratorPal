package GeneratorPal.FilterObjects;

import GeneratorPal.StructureObjects.AssetPack;

import java.util.HashSet;

public class TagGroup extends FilterObject {
    //Optional fields
    private HashSet<Tag> tags;
    private HashSet<TagGroup> requiredTagGroups;

    //Constructor
    public TagGroup(String name, String description, AssetPack assetPack) {
        super(name, description, assetPack);
        this.tags = new HashSet<>();
        this.requiredTagGroups = new HashSet<>();
    }

    //Secondary Methods
    ///Getters and Setters
    public HashSet<Tag> getTags() {
        return tags;
    }
    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }
    public HashSet<TagGroup> getRequiredTagGroups() {
        return requiredTagGroups;
    }
    public void setRequiredTagGroups(HashSet<TagGroup> requiredTagGroups) {
        this.requiredTagGroups = requiredTagGroups;
    }


    //Old Stuff
//    public TagGroup(String name, AssetPack assetPack){}
//
//    public HashMap<String, Tag> getTags(){}
//    public void setTags(){}
//    public void addTag(){}
//    public void removeTag(){}
//
//    public HashMap<String,TagGroup> getRequiredTagGroups(){}
//    public void setRequiredTagGroups(){}
//    public void addRequiredTagGroup(TagGroup tagGroup){}
//    public void removeRequiredTagGroup(){}
//
//    @Override
//    public void updateAssetPack() {    }
}
