package main.obsolete;

public abstract class Dataset {
    protected String name;
    protected String description;

    public Dataset(String name) {
        this.name = name;
        this.description = null;
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
