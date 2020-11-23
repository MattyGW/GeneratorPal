package main.obsolete;

import main.StructureObjects.AssetPack;
import main.FilterObjects.Tag;

import java.util.HashMap;

public class Subpack_Tag extends AssetPack {
    public HashMap<String, Tag> tags;

    public Subpack_Tag(String name) {
        super(name);
        this.tags = new HashMap<String, Tag>();
    }

    public void addTag(Tag tag){
        tags.put(tag.getName(),tag);
    }
    public void removeTag(String name){
        tags.remove(name);
    }
    public HashMap<String, Tag> getTags() {
        return tags;
    }
    public void setActors(HashMap<String, Tag> tags) {
        this.tags = tags;
    }
}