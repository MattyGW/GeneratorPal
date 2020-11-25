package main.FilterObjects;

import main.StructureObjects.AssetPack;

public abstract class FilterObject {
    //required fields
    private String name;
    private String description;
    private AssetPack assetPack;

    //Constructor
    public FilterObject(String name, String description, AssetPack assetPack) {
        this.name = name;
        this.description = description;
        this.assetPack = assetPack;
    }

    //Secondary Methods
    //Getters and Setters
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
    public AssetPack getAssetPack() {
        return assetPack;
    }
    public void setAssetPack(AssetPack assetPack) {
        this.assetPack = assetPack;
    }


    //old stuff
//    //Constructors
//    protected FilterObject() {
//    }
//
//    //Storage Methods
//    public abstract void updateAssetPack();
//
//    //Getters & Setters-
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
