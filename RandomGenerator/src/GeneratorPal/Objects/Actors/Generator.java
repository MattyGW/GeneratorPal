package GeneratorPal.Objects.Actors;

import GeneratorPal.Managers.ActorManager;
import GeneratorPal.Objects.DataObjects.ActorOutput;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Generator extends Actor{
    // Each step will occur randomly between x and y times. (x can equal y)
    // When a step alters fields they are global.
    private ArrayList<Pair<String,Pair<Integer,Integer>>> actions;


    public Generator(String name, ActorManager actorManager) {
        super(name, actorManager);
        this.actions = new ArrayList<>();
    }
    public Generator(String name, ActorManager actorManager, String description, ArrayList<Pair<String,Pair<Integer,Integer>>> actions, HashMap<String, Integer> fields) {
        super(name, actorManager, description, fields);
        this.actions = actions;
    }

    @Override
    public ActorOutput run(HashMap<String, Integer> passedFields) {
        ActorOutput actorOutput = new ActorOutput(this.getName());
        HashMap<String, Integer> actorsFields = (HashMap<String, Integer>) fields.clone();
        integrateFields(actorsFields,passedFields);
        for(Pair<String, Pair<Integer, Integer>> pair: actions){
            for(int i = (int) ((Pair) pair.getValue()).getKey(); i > 0; i --){
                actorOutput.getElements().add((actorManager.getActors().get((String)pair.getKey())).run(actorsFields));
            }
        }
        return actorOutput;
    }
}