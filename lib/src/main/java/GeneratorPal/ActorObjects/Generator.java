package GeneratorPal.ActorObjects;

import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import GeneratorPal.StructureObjects.AssetPack;
import GeneratorPal.StructureObjects.Project;

public class Generator extends Actor{
    //Optional Fields
    private ArrayList<Pair<Actor,Integer>> stages;

    //Constructor
    public Generator(String name, Project project, AssetPack assetPack) {
        //required Fields
        super(name, project, assetPack);
        //optional Fields
        this.stages = new ArrayList<>();
    }

    //Secondary Methods
    ///Getters & Setters
    public ArrayList<Pair<Actor, Integer>> getStages() {
        return stages;
    }
    public void setStages(ArrayList<Pair<Actor, Integer>> stages) {
        this.stages = stages;
    }


    //Old Stuff
//    public Generator(String name, AssetPack assetPack){}
//
//    public ArrayList<Pair<Actor,Integer>> getStages(){}
//    public void newStage(){}
//    public void addStage(){}
//    public void removeStage(){}
//    public void setStages(){}
//
//    @Override
//    public void updateAssetPack() {}
}
