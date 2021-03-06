package GeneratorPal.ActorObjects;

import java.util.HashMap;

import GeneratorPal.FilterObjects.Tag;
import GeneratorPal.StructureObjects.AssetPack;
import GeneratorPal.StructureObjects.Project;

public class Selector extends Actor{
    //Optional Fields
    private HashMap<Tag, Integer> tagsNWeights;

    //Constructor
    public Selector(String name, Project project, AssetPack assetPack) {
        //required Fields
        super(name, project, assetPack);
        //optional Fields
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
