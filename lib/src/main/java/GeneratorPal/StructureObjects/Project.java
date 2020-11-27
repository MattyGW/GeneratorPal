package main.StructureObjects;

import main.ActorObjects.*;
import main.FilterObjects.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Project{
    //Required Fields
    private String name;
    //Optional Fields
    private String fileAddress;
    private HashSet<AssetPack> assetPacks;
    private HashSet<Actor> actors;
    private HashSet<Option> options;
    private HashSet<Tag> tags;
    private HashSet<TagGroup> tagGroups;
    private HashSet<Variable> variables;

    //Constructors
    public Project(String name) {
        this.name = name;
        this.fileAddress = null;
        this.assetPacks = new HashSet<>();
        this.actors = new HashSet<>();
        this.options = new HashSet<>();
        this.tags = new HashSet<>();
        this.tagGroups = new HashSet<>();
        this.variables = new HashSet<>();
    }

    //Primary Methods
    public Actor createSelector(String name, AssetPack assetPack){return null;}
    public Actor importSelector(){return null;}

    public Actor createGenerator(){return null;}
    public Actor importGenerator(){return null;}

    public AssetPack createAssetPack(String name){return null;}
    public AssetPack importAssetPack(){return null;}

    public Tag createTag(){return null;}
    public Tag importTag(){return null;}

    public Variable createVariable(){return null;}
    public Variable importVariable(){return null;}

    public TagGroup createTagGroup(){return null;}
    public TagGroup importTagGroup(){return null;}

    public Option createOption(){return null;}
    public Option importOption(){return null;}

    //Secondary Methods
    ///Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFileAddress() {
        return fileAddress;
    }
    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
    public HashSet<AssetPack> getAssetPacks() {
        return assetPacks;
    }
    public void setAssetPacks(HashSet<AssetPack> assetPacks) {
        this.assetPacks = assetPacks;
    }
    public HashSet<Actor> getActors() {
        return actors;
    }
    public void setActors(HashSet<Actor> actors) {
        this.actors = actors;
    }
    public HashSet<Option> getOptions() {
        return options;
    }
    public void setOptions(HashSet<Option> options) {
        this.options = options;
    }
    public HashSet<Tag> getTags() {
        return tags;
    }
    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }
    public HashSet<TagGroup> getTagGroups() {
        return tagGroups;
    }
    public void setTagGroups(HashSet<TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
    }
    public HashSet<Variable> getVariables() {
        return variables;
    }
    public void setVariables(HashSet<Variable> variables) {
        this.variables = variables;
    }
    
    //
//    public Project(String name) {
//        super(name);
//        this.tagGroups = new HashMap<String, TagGroup>();
//        this.options = new HashMap<String, Option>();
//        this.tags = new HashMap<String, Tag>();
//        this.variables = new HashMap<String, Variable>();
//        this.actors = new HashMap<String, Actor>();
//        this.assetPacks = new HashMap<String, AssetPack>();
//    }
//
//    //Getters & Setters
//    public HashMap<String, TagGroup> getTagGroups() {
//        return tagGroups;
//    }
//    public HashMap<String, Option> getOptions() {
//        return options;
//    }
//    public HashMap<String, Tag> getTags() {
//        return tags;
//    }
//    public HashMap<String, Variable> getVariables() {
//        return variables;
//    }
//    public HashMap<String, Actor> getActors() {
//        return actors;
//    }
//    public HashMap<String, AssetPack> getAssetPacks() {
//        return assetPacks;
//    }
//    public void setTagGroups(HashMap<String, TagGroup> tagGroups) {
//        this.tagGroups = tagGroups;
//    }
//    public void setOptions(HashMap<String, Option> options) {
//        this.options = options;
//    }
//    public void setTags(HashMap<String, Tag> tags) {
//        this.tags = tags;
//    }
//    public void setVariables(HashMap<String, Variable> variables) {
//        this.variables = variables;
//    }
//    public void setActors(HashMap<String, Actor> actors) {
//        this.actors = actors;
//    }
//    public void setAssetPacks(HashMap<String, AssetPack> assetPacks) {
//        this.assetPacks = assetPacks;
//    }
//
//    //Factory Methods
//    public TagGroup newTagGroup(String name, AssetPack assetPack) {
//        TagGroup tagGroup = new TagGroup(name, assetPack);
//        tagGroups.put(name,tagGroup);
//        return tagGroup;
//    }
//    public Option newOption(String name, AssetPack assetPack){
//        Option option = new Option(name, assetPack);
//        options.put(name,option);
//        return option;
//    }
//    public Tag newTag(String name, AssetPack assetPack){
//        Tag tag = new Tag(name, assetPack);
//        tags.put(name,tag);
//        return tag;
//    }
//    public Variable newVariable(String name, AssetPack assetPack){
//        Variable variable = new Variable(name, assetPack);
//        variables.put(name,variable);
//        return variable;
//    }
//    public Selector newSelector(String name, AssetPack assetPack){
//        Selector selector = new Selector(name, assetPack);
//        actors.put(name,selector);
//        return selector;
//    }
//    public Generator newGenerator(String name, AssetPack assetPack){
//        Generator generator = new Generator(name, assetPack);
//        actors.put(name,generator);
//        return generator;
//    }
//    public AssetPack newAssetPack(String name){
//        AssetPack assetPack = new AssetPack(name);
//        assetPacks.put(name,assetPack);
//        return assetPack;
//    }
//    public void deleteTagGroup(String name){
//        tagGroups.remove(name);
//    }
//    public void deleteOption(String name){
//        options.remove(name);
//    }
//    public void deleteTag(String name){
//        tags.remove(name);
//    }
//    public void deleteVariable(String name){
//        variables.remove(name);
//    }
//    public void deleteActor(String name){
//        actors.remove(name);
//    }
//    public void deleteAssetPack(String name){
//        assetPacks.remove(name);
//    }
//
//    //Exporting Methods
//    public void updateAssetPacks(){
//        Iterator iterator = assetPacks.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((AssetPack)entry.getValue()).clear();
//        }
//        iterator = tagGroups.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((TagGroup)entry.getValue()).updateAssetPack();
//        }
//        iterator = tags.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((Tag)entry.getValue()).updateAssetPack();
//        }
//        iterator = options.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((Option)entry.getValue()).updateAssetPack();
//        }
//        iterator = variables.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((Variable)entry.getValue()).updateAssetPack();
//        }
//        iterator = actors.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry entry = (Map.Entry)iterator.next();
//            ((Actor)entry.getValue()).updateAssetPack();
//        }
//    }
//    public void exportAssetPack(){}
//    public void saveProject(){}
//
//    //Importing Methods
//    public boolean importData(String[] line) throws Exception {
//        Boolean returnValue = false;
//        if(String.valueOf(line[0].charAt(0)).equals("!")){
//            switch (String.valueOf(line[0].charAt(1))) {
//                case "3" -> returnValue = importTagGroup(line);
//                case "4" -> returnValue = importTag(line);
//                case "5" -> returnValue = importOption(line);
//                case "6" -> returnValue = importVariable(line);
//                case "7" -> returnValue = importActor(line);
//                default -> throw new Exception("Incorrect Second char Entry");
//            }
//        } else {
//            throw new Exception("Incorrect First char Entry");
//        }
//        return returnValue;
//    }
//    private boolean importTagGroup(String[] data){
//        if(tagGroups.containsKey(data[0].substring(3))){
//            TagGroup tagGroup = tagGroups.get(data[0].substring(3));
//            for(int i = 1; i < data.length; i++){
//                switch (i){
//                    case 1 -> tagGroup.(data[0].substring(3),this.getAssetPacks().get(data[1].substring(3)));
//                    case 2 -> tagGroup.setDescription(data[3]);
//                    default -> tagGroup.addRequiredTagGroup(getTagGroups().get(data[i].substring(3)));
//                    //if not present get will return null
//                }
//            }
//        } else {
//            TagGroup tagGroup = null;
//            for(int i = 1; i < data.length; i++){
//                switch (i){
//                    case 1 -> tagGroup = new TagGroup(data[0].substring(3),this.getAssetPacks().get(data[1].substring(3)));
//                    case 2 -> tagGroup.setDescription(data[3]);
//                    default -> tagGroup.addRequiredTagGroup(getTagGroups().get(data[i].substring(3)));
//                    //if not present get will return null
//                }
//            }
//        }
//    }
//    private boolean importTag(String[] data){}
//    private boolean importOption(String[] data){}
//    private boolean importVariable(String[] data){}
//    private boolean importActor(String[] data){}
//
//    public Boolean newTagGroup(String[] data) {
//        TagGroup tagGroup = null;
//        for(int i = 1; i < data.length; i++){
//            switch (i){
//                case 1 -> tagGroup = new TagGroup(data[0].substring(3),this.getAssetPacks().get(data[1].substring(3)));
//                case 2 -> tagGroup.setDescription(data[3]);
//                default -> tagGroup.addRequiredTagGroup(getTagGroups().get(data[i].substring(3)));
//                //if not present get will return null
//            }
//        }
//        return tagGroup;
//    }
//    public void importAssetPack(){}
}
