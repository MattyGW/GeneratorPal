package GeneratorPal.Managers;

import GeneratorPal.Datasets.ItemDataset;
import GeneratorPal.Datasets.TagDataset;

import java.util.*;

public class GeneratorPalManager {
    private static GeneratorPalManager   uniqueInstance;
    //Active Fields
    private final HashMap<String,ActorManager> allActorManagers;
    private final HashMap<String,ItemDataset>  allItemDatasets;
    private final HashMap<String,TagDataset>   allTagDatasets;

    //Personal Constructor Methods
    private GeneratorPalManager(){
        //Active Fields
        allActorManagers = new HashMap<String,ActorManager>();
        allItemDatasets  = new HashMap<String, ItemDataset>();
        allTagDatasets   = new HashMap<String, TagDataset>();
    }
    public static GeneratorPalManager getInstance(){
        return Objects.requireNonNullElseGet(uniqueInstance, GeneratorPalManager::new);
    }
}