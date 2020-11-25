package main.StructureObjects;

import main.ActorObjects.Actor;
import main.FilterObjects.Option;
import main.FilterObjects.Tag;
import main.FilterObjects.TagGroup;
import main.FilterObjects.Variable;

import java.util.HashSet;

public class AssetPack {
    private String name;
    private HashSet<Tag> tags;
    private HashSet<Variable> variables;
    private HashSet<TagGroup> tagGroups;
    private HashSet<Option> options;
    private HashSet<Actor> actors;

    //Constructors
    public AssetPack(String name) {
        this.name = name;
        this.tags = new HashSet<>();
        this.variables = new HashSet<>();
        this.tagGroups = new HashSet<>();
        this.options = new HashSet<>();
        this.actors = new HashSet<>();
    }

    //Secondary Methods
    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HashSet<Tag> getTags() {
        return tags;
    }
    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }
    public HashSet<Variable> getVariables() {
        return variables;
    }
    public void setVariables(HashSet<Variable> variables) {
        this.variables = variables;
    }
    public HashSet<TagGroup> getTagGroups() {
        return tagGroups;
    }
    public void setTagGroups(HashSet<TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
    }
    public HashSet<Option> getOptions() {
        return options;
    }
    public void setOptions(HashSet<Option> options) {
        this.options = options;
    }
    public HashSet<Actor> getActors() {
        return actors;
    }
    public void setActors(HashSet<Actor> actors) {
        this.actors = actors;
    }


    //
//    public HashMap<String, TagGroup> getActors(){}
//    public HashMap<String, TagGroup> getOptionss(){}
//    public HashMap<String, TagGroup> getVariables(){}
//    public HashMap<String, TagGroup> getTags(){}
//    public HashMap<String, TagGroup> getTagGroups(){}
//
//    public void addSubpack_Actor(Subpack_Actor subpack_actor){
//        subpack_Actors.put(subpack_actor.getName(),subpack_actor);
//    }
//    public void removeSubpack_Actor(String name){
//        subpack_Actors.remove(name);
//    }
//    public HashMap<String, Subpack_Actor> getSubpack_Actors() {
//        return subpack_Actors;
//    }
//    public void setSubpack_Actors(HashMap<String, Subpack_Actor> subpack_Actors) {
//        this.subpack_Actors = subpack_Actors;
//    }
//
//    public void addSubpack_Option(Subpack_Option subpack_option){
//        subpack_Options.put(subpack_option.getName(), subpack_option);
//    }
//    public void removeSubpack_Option(String name){
//        subpack_Options.remove(name);
//    }
//    public HashMap<String, Subpack_Option> getSubpack_Options() {
//        return subpack_Options;
//    }
//    public void setSubpack_Options(HashMap<String, Subpack_Option> subpack_Options) {
//        this.subpack_Options = subpack_Options;
//    }
//
//    public void addSubpack_Variable(Subpack_Variable subpack_variable){
//        subpack_Variables.put(subpack_variable.getName(),subpack_variable);
//    }
//    public void removeSubpack_Variable(String name){
//        subpack_Variables.remove(name);
//    }
//    public HashMap<String, Subpack_Variable> getSubpack_Variables() {
//        return subpack_Variables;
//    }
//    public void setSubpack_Variables(HashMap<String, Subpack_Variable> subpack_Variables) {
//        this.subpack_Variables = subpack_Variables;
//    }
//
//    public void addSubpack_Tags(Subpack_Tag subpack_tag){
//        subpack_Tags.put(subpack_tag.getName(),subpack_tag);
//    }
//    public void removeSubpack_Tags(String name){
//        subpack_Tags.remove(name);
//    }
//    public HashMap<String, Subpack_Tag> getSubpack_Tags() {
//        return subpack_Tags;
//    }
//    public void setSubpack_Tags(HashMap<String, Subpack_Tag> subpack_Tags) {
//        this.subpack_Tags = subpack_Tags;
//    }
//
//    public void addSubpack_TagGroup(Subpack_TagGroup subpack_tagGroup){
//        subpack_TagGroups.put(subpack_tagGroup.getName(),subpack_tagGroup);
//    }
//    public void removeSubpack_TagGroup(String name){
//        subpack_TagGroups.remove(name);
//    }
//    public HashMap<String, Subpack_TagGroup> getSubpack_TagGroups() {
//        return subpack_TagGroups;
//    }
//    public void setSubpack_TagGroups(HashMap<String, Subpack_TagGroup> subpack_TagGroups) {
//        this.subpack_TagGroups = subpack_TagGroups;
//    }
//
//    public void clear() {
//    }
}
