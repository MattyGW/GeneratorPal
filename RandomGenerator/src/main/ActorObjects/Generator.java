package main.ActorObjects;

import javafx.util.Pair;
import main.StructureObjects.AssetPack;
import main.StructureObjects.Project;

import java.util.ArrayList;

public class Generator extends Actor{
    //Optional Fields
    private ArrayList<Pair<Actor,Integer>> stages;

    //Constructor
    public Generator(String name, Project project, AssetPack assetPack) {
        super(name, project, assetPack);
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
