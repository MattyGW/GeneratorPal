package GeneratorPal.FilterObjects;

import GeneratorPal.StructureObjects.AssetPack;

import java.util.HashMap;

public class Variable extends FilterObject {
    //Optional fields
    private HashMap<Tag, Integer> tagInteraction;

    //Constructor
    public Variable(String name, String description, AssetPack assetPack) {
        //Required Fields
        super(name, description, assetPack);
        //Optional Fields
        this.tagInteraction = new HashMap<>();
    }

    //Secondary Methods
    ///Getters and Setters
    public HashMap<Tag, Integer> getTagInteraction() {
        return tagInteraction;
    }
    public void setTagInteraction(HashMap<Tag, Integer> tagInteraction) {
        this.tagInteraction = tagInteraction;
    }
    
    //Old stuff
//    public Variable(String name, AssetPack assetPack){}
//
//    public void replaceTagInteractionValue(){}
//    public void setTagInteractions(){}
//    public Integer getTagInteraction(){}
//    public void removeTagInteraction(){}
//    public void addTagInteraction(){}
//
//    @Override
//    public void updateAssetPack() {
//
//    }
}
