package main.ActorObjects;

import javafx.util.Pair;
import main.FilterObjects.Option;
import main.FilterObjects.Tag;
import main.StructureObjects.AssetPack;
import main.StructureObjects.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class Selector extends Actor{
    //Optional Fields
    private HashMap<Tag, Integer> tagsNWeights;

    //Constructor
    public Selector(String name, Project project, AssetPack assetPack) {
        super(name, project, assetPack);
        this.tagsNWeights = new HashMap<>();
    }

    //Secondary Methods
    ///Getters & Setters
    public HashMap<Tag, Integer> getTagsNWeights() {
        return tagsNWeights;
    }
    public void setTagsNWeights(HashMap<Tag, Integer> tagsNWeights) {
        this.tagsNWeights = tagsNWeights;
    }


//    //Old Stuff
//    public Selector(String name, AssetPack assetPack){}
//
//    public HashMap<Tag, Integer> getSelectedTags(){}
//    public void addSelectorTag(){}
//    public void removeSelectedTag(){}
//    public void setSelectedTags(){}
//    public void replaceSelectedTagWeight(){}
//
//    public void updateOptions(){}
//    public HashMap<String, Option> getOptions(){}
//
//    @Override
//    public void updateAssetPack() {
//
//    }
}
