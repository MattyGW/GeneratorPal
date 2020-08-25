package GeneratorPal.Objects.Actors;

import GeneratorPal.Managers.ActorManager;
import GeneratorPal.Objects.DataObjects.ActorOutput;
import GeneratorPal.Objects.DataObjects.Item;
import GeneratorPal.Objects.DataObjects.Tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public abstract class Actor {
    protected ActorManager actorManager;
    protected String name;
    protected String description;
    protected HashMap<String, Integer> fields;

    public Actor(String name, ActorManager actorManager) {
        //Constructor for a making new actors
        System.out.println("Constructing new Actor: \"" + name + "\"");
        this.actorManager   = actorManager;
        this.name           = name;
        this.fields         = new HashMap<String, Integer>();
    }
    public Actor(String name, ActorManager actorManager, String description, HashMap<String, Integer> fields) {
        //Constructor for loading saved actors
        System.out.println("Loading Actor: \"" + name + "\"");
        this.actorManager   = actorManager;
        this.name           = name;
        this.description    = description;
        this.fields         = fields;
    }

    abstract public ActorOutput run(HashMap<String, Integer> passedFields);

    protected void integrateFields(HashMap<String, Integer> activeFields, HashMap<String, Integer> passiveFields){
        Iterator iterator = passiveFields.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry passedField = (Map.Entry) iterator.next();
            if(activeFields.containsKey(passedField.getKey())){
                activeFields.replace((String) passedField.getKey(),(Integer)passedField.getValue());
            }
            else {
                activeFields.put((String)passedField.getKey(),(Integer)passedField.getValue());
            }
        }
    }

    //RandomMethods
    public void addItemOutput(GeneratorPalOld.Item item) {
        if (output.containsKey(item)) {
            output.replace(item, output.get(item) + 1);
        } else {
            output.put(item, 1);
        }
    }

    public GeneratorPalOld.Item getRandomItem(HashMap<String, GeneratorPalOld.Item> itemSelection) throws Exception {
        Integer randomNumber = new Random().nextInt(itemSelection.size());
        Iterator iterator = itemSelection.entrySet().iterator();
        while (iterator.hasNext()) {
            randomNumber -= 1;
            Map.Entry entry = (Map.Entry) iterator.next();
            if (randomNumber <= 0) {
                return (Item) entry.getValue();
            }
        }
        throw new Exception("Error: A item wasn't returned by getRandomItem()");
    }

    public Tag getWeightedRandomTag(HashMap<Tag, Integer> remainingTag) throws Exception {
        Iterator iterator = remainingTag.entrySet().iterator();
        Integer totalWeight = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            totalWeight += (Integer) ((Map.Entry) iterator.next()).getValue();
        }
        Integer randomNumber = new Random().nextInt(totalWeight);
        iterator = remainingTag.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            randomNumber -= (Integer) entry.getValue();
            if (randomNumber <= 0) {
                return (Tag) entry.getKey();
            }
        }
        throw new Exception("Error: A Tag wasn't returned by getWeightedRandomItem()");
    }

    public ActorManager getActorManager() {
        return actorManager;
    }

    public void setActorManager(ActorManager actorManager) {
        this.actorManager = actorManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
