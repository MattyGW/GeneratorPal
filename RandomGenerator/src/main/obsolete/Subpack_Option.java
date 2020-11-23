package main.obsolete;

import main.StructureObjects.AssetPack;
import main.FilterObjects.Option;

import java.util.HashMap;

public class Subpack_Option extends AssetPack {
    public HashMap<String, Option> options;

    public Subpack_Option(String name) {
        super(name);
        this.options = new HashMap<String, Option>();
    }

    public void addOption(Option option){
        options.put(option.getName(),option);
    }
    public void removeOption(String name){
        options.remove(name);
    }
    public HashMap<String, Option> getOptions() {
        return options;
    }
    public void setActors(HashMap<String, Option> options) {
        this.options = options;
    }
}