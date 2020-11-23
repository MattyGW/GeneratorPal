package main.FilterObjects;

import main.StructureObjects.AssetPack;

import java.util.HashMap;
import java.util.HashSet;

public class TagGroup extends FilterObject {
    //Optional fields
    private HashSet<Tag> tags;
    private HashSet<TagGroup> requiredTagGroups;

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
