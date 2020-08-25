package GeneratorPal.Managers;

import GeneratorPal.Datasets.ItemDataset;
import GeneratorPal.Datasets.TagDataset;
import GeneratorPal.Objects.Actors.Actor;

import java.util.*;

public class ActorManager {
    //References
    private String                      name;
    private GeneratorPalManager         generatorPalManager;
    //Active Fields
    private HashMap<String, Actor>       actors;
    //Access Fields
    private HashMap<String, ItemDataset> selectedItemDatasets;
    private HashMap<String, TagDataset>  selectedTagDatasets;

    //Creating a new actorManager
    private ActorManager(String name, GeneratorPalManager generatorPalManager){
        System.out.println("Constructing new Actor Manager \"" + name + "\"");
        //References
        this.name = name;
        this.generatorPalManager = generatorPalManager;
        //Active Fields
        this.actors = new HashMap<String, Actor.Actor>();
        //Access Fields
        this.selectedItemDatasets = new HashMap<String,ItemDataset>();
        this.selectedTagDatasets  = new HashMap<String,TagDataset>();
        this.selectedActorItems   = new HashMap<String,ActorItem>();
    }
    //Loading a saved actorManager
    private ActorManager( String name,
                          GeneratorPalManager generatorPalManager,
                          HashMap<String, Actor.Actor> actors,
                          HashMap<String,ActorItem> actorItems,
                          HashMap<String,ItemDataset> itemDatas,
                          HashMap<String,TagDataset> tagDatas){
        System.out.println("Constructing Actor Manager \"" + name + "\" from save");
        //References
        this.name = name;
        this.generatorPalManager = generatorPalManager;
        //Active Fields
        this.actors     = actors;
        //Access Fields
        this.selectedItemDatasets = itemDatas;
        this.selectedTagDatasets  = tagDatas;
        this.selectedActorItems   = actorItems;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public GeneratorPalManager getGeneratorPalManager() {
        return generatorPalManager;
    }
    public void setGeneratorPalManager(GeneratorPalManager generatorPalManager) {
        this.generatorPalManager = generatorPalManager;
    }
    public HashMap<String, Actor> getActors() {
        return actors;
    }
    public void setActors(HashMap<String, Actor> actors) {
        this.actors = actors;
    }
    public HashMap<String, ItemDataset> getSelectedItemDatasets() {
        return selectedItemDatasets;
    }
    public void setSelectedItemDatasets(HashMap<String, ItemDataset> selectedItemDatasets) {
        this.selectedItemDatasets = selectedItemDatasets;
    }
    public HashMap<String, TagDataset> getSelectedTagDatasets() {
        return selectedTagDatasets;
    }
    public void setSelectedTagDatasets(HashMap<String, TagDataset> selectedTagDatasets) {
        this.selectedTagDatasets = selectedTagDatasets;
    }
}
