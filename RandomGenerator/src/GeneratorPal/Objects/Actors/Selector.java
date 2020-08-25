package GeneratorPal.Objects.Actors;

import GeneratorPal.Managers.ActorManager;
import GeneratorPal.Objects.DataObjects.ActorOutput;
import GeneratorPal.Objects.DataObjects.Item;
import GeneratorPal.Objects.DataObjects.Tag;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Selector extends Actor{
    private Integer outputCount;
        //This determines the number of times the actor is called.
    private HashMap<String, Integer> tags;
        //These will be the selected tags for the actor
    private HashMap<String, Item> availiableItems;
        //This list will be constructed from the remaining selected tags

    public Selector(String name, ActorManager actorManager) {
        super(name, actorManager);
        this.outputCount = 1;
        this.tags = new HashMap<String, Integer>();
        this.availiableItems = new HashMap<String, Item>();
    }

    public Selector(String name, ActorManager actorManager, String description, HashMap<String, Integer> fields,
                    Integer outputCount, HashMap<String, Integer> tags) {
        super(name, actorManager, description, fields);
        this.outputCount = outputCount;
        this.tags = tags;
        this.availiableItems = null; //Add a method to construct available items
    }

    @Override
    public ActorOutput run(HashMap<String, Integer> passedFields) {
        ActorOutput actorOutput = new ActorOutput(this.getName());
        HashMap<String, Integer> actorsFields = (HashMap<String, Integer>) fields.clone();
        integrateFields(actorsFields,passedFields);
        HashMap<String,Item> remainingItems = (HashMap<String, Item>)availiableItems.clone();

        
        HashMap<Tag, Integer> remainingTags = (HashMap<Tag, Integer>) selectedTags.clone();
        //Run the selector per amount called
        for (int i = 1; i <= amountCalled; i++) {
            while (!remainingTags.isEmpty()) {
                Tag selectedTag = getWeightedRandomTag(remainingTags);
                //Go through the selected tag and its siblings
                for (Tag tag : selectedTag.getTagGroup().getTags()) {
                    if (!(tag.equals(selectedTag))) {
                        //Remove the siblings items from the remaining items
                        for (GeneratorPalOld.Item item : tag.getItems()) {
                            remainingItems.remove(item);
                        }
                    }
                    //Remove selected and sibling tags from remaining tags
                    remainingTags.remove(tag);
                }
            }
            addItemOutput(getRandomItem(remainingItems));
        }
        //Output the items
        System.out.println("Selector Complete");
        Iterator iterator = output.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(" - Item \"" + ((GeneratorPalOld.Item) entry.getKey()).getName() + "\" count \"" + entry.getValue() + "\"");
        }
    }
}
