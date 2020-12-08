package GeneratorPal.ActorObjects;

import java.util.HashMap;

import GeneratorPal.FilterObjects.Variable;
import GeneratorPal.StructureObjects.AssetPack;
import GeneratorPal.StructureObjects.Project;

public abstract class Actor {
    //required fields
    private String name;
    private Project project;
    private AssetPack assetPack;
    //optional fields
    private Boolean localisedVariables;
    private HashMap<Variable, Integer> variables;

    //Constructor
    public Actor(String name, Project project, AssetPack assetPack) {
        //required fields
        this.name = name;
        this.project = project;
        this.assetPack = assetPack;
        //optional fields
        this.localisedVariables = false;
        this.variables = new HashMap<>();
    }

    //Primary Methods
    public void run(){}

    //secondary Methods
    ///Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public AssetPack getAssetPack() {
        return assetPack;
    }
    public void setAssetPack(AssetPack assetPack) {
        this.assetPack = assetPack;
    }
    public Boolean getLocalisedVariables() {
        return localisedVariables;
    }
    public void setLocalisedVariables(Boolean localisedVariables) {
        this.localisedVariables = localisedVariables;
    }
    public HashMap<Variable, Integer> getVariables() {
        return variables;
    }
    public void setVariables(HashMap<Variable, Integer> variables) {
        this.variables = variables;
    }


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
