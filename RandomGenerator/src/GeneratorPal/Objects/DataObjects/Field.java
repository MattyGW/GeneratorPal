package GeneratorPal.Objects.DataObjects;

import GeneratorPal.Datasets.FieldDataset;

import java.util.HashMap;

public class Field {
    //References
    private FieldDataset fieldDataset;
    //Data Fields
    private String name;
    private String description;
    private HashMap<Tag, Integer> defaultFieldOnTagInteractions;

    //Constructor
    public Field(String name){
        this.name = name;
    }

    //Getters & Setters
    public FieldDataset getFieldDataset() {
        return fieldDataset;
    }
    public void setFieldDataset(FieldDataset fieldDataset) {
        this.fieldDataset = fieldDataset;
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
