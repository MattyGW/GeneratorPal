package main.obsolete;

import main.StructureObjects.AssetPack;
import main.FilterObjects.TagGroup;

import java.util.HashMap;

public class Subpack_TagGroup extends AssetPack {
    public HashMap<String, TagGroup> tagGroups;

    public Subpack_TagGroup(String name) {
        super(name);
        this.tagGroups = new HashMap<String, TagGroup>();
    }

    public void addTagGroup(TagGroup tagGroup){
        tagGroups.put(tagGroup.getName(),tagGroup);
    }
    public void removeTagGroup(String name){
        tagGroups.remove(name);
    }
    public HashMap<String, TagGroup> getTagGroups() {
        return tagGroups;
    }
    public void setActors(HashMap<String, TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
    }
}