package GeneratorPal.Objects.DataObjects;

import GeneratorPal.Objects.Actors.Actor;

import java.util.ArrayList;

public class ActorOutput{
    private String name;
    private String description;
    private Actor actor; //This is the actor that generated this output
    private ArrayList<Object> Elements;

    public ActorOutput(String name){

    }

    public void implementActorOutput(Actor actor){
        //Calls the actor and records its answer
        ActorOutput actorOutput = actor.run();

    }

    public Actor getActor() {
        return actor;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public ArrayList<Item> getItems() {
        return Items;
    }
    public void setItems(ArrayList<Item> items) {
        Items = items;
    }
    public ArrayList<ActorOutput> getActorItems() {
        return ActorItems;
    }
    public void setActorItems(ArrayList<ActorOutput> actorItems) {
        ActorItems = actorItems;
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
    public ArrayList<Object> getElements() {
        return Elements;
    }
    public void setElements(ArrayList<Object> elements) {
        Elements = elements;
    }
}