package main.ActorObjects;

import main.StructureObjects.AssetPack;
import main.StructureObjects.Project;
import main.FilterObjects.Variable;
import java.util.HashMap;

public abstract class Actor {
    //required fields
    private String name;
    private Project project;
    private AssetPack assetPack;
    //optional fields
    private Boolean localisedVariables;
    private HashMap<Variable, Integer> variables;

//    //Old Stuff
//    public Actor(){}
//
//    public abstract void updateAssetPack();
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public HashMap<Variable, Integer> getVariables() {
//        return Variables;
//    }
//    public void setVariables(HashMap<Variable, Integer> variables) {
//        Variables = variables;
//    }
//    public void replaceVariableValue(){}
//    public void removeVariable(){}
//    public void addVariable(){}
//
//    public Boolean isLocalised(){}
//    public void setLocalisedVariables(){}
//
//    public Subpack_Actor getAssetPack(){}
//    public void setAssetPack(){}
//
//    public Option createActorOption(){}
//    public void runActor(){}
//    public void saveActor(){}
//    public void updateActor(){}
}
