package main.FilterObjects;

import main.StructureObjects.AssetPack;

import java.util.HashMap;

public class Tag extends FilterObject {
    //Required fields
    private TagGroup tagGroup;
    //Optional fields
    private Integer weight;
    private HashMap<Variable, Integer> variableInteractions;

    //Constructor
    public Tag(String name, String description, AssetPack assetPack, TagGroup tagGroup) {
        super(name, description, assetPack);
        this.weight = 0;
        this.tagGroup = tagGroup;
        this.variableInteractions = new HashMap<>();
    }

    //Secondary Methods
    //Getters and Setters
    public TagGroup getTagGroup() {
        return tagGroup;
    }
    public void setTagGroup(TagGroup tagGroup) {
        this.tagGroup = tagGroup;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public HashMap<Variable, Integer> getVariableInteractions() {
        return variableInteractions;
    }
    public void setVariableInteractions(HashMap<Variable, Integer> variableInteractions) {
        this.variableInteractions = variableInteractions;
    }


    //Old Stuff
//    public Tag(String name, AssetPack assetPack){}
//
//    public void setWeight(){}
//    public Integer getWeight(){}
//
//    public void setTagGroup(){}
//    public TagGroup getTagGroup(){}
//
//    public void addVariableInteraction(){}
//    public void removeVariableInteraction(){}
//    public void setVariableInteractions(){}
//    public HashMap<Variable,Integer> getVariableInteractions(){}
//    public void replaceVariableInteractionValue(){}
//
//    public void addOption(){}
//    public void removeOption(){}
//    public HashMap<String,Option> getOptions(){}
//    public void setOptions(){}
//
//    @Override
//    public void updateAssetPack() {
//
//    }
}
