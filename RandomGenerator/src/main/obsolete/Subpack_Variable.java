package main.obsolete;

import main.StructureObjects.AssetPack;
import main.FilterObjects.Variable;

import java.util.HashMap;

public class Subpack_Variable extends AssetPack {
    public HashMap<String, Variable> variables;

    public Subpack_Variable(String name) {
        super(name);
        this.variables = new HashMap<String, Variable>();
    }

    public void addVariable(Variable variable){
        variables.put(variable.getName(),variable);
    }
    public void removeVariable(String name){
        variables.remove(name);
    }
    public HashMap<String, Variable> getVariables() {
        return variables;
    }
    public void setActors(HashMap<String, Variable> variables) {
        this.variables = variables;
    }
}