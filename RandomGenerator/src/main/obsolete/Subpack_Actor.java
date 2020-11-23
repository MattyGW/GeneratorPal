package main.obsolete;

import main.ActorObjects.Actor;

import java.util.HashMap;

public class Subpack_Actor extends Dataset {
    private HashMap<String, Actor> actors;

    public Subpack_Actor(String name) {
        super(name);
        this.actors = new HashMap<String, Actor>();
    }

    public void addActor(Actor actor){
        actors.put(actor.getName(),actor);
    }
    public void removeActor(String name){
        actors.remove(name);
    }
    public HashMap<String, Actor> getActors() {
        return actors;
    }
    public void setActors(HashMap<String, Actor> actors) {
        this.actors = actors;
    }
}
