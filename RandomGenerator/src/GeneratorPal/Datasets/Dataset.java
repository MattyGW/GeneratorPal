package GeneratorPal.Datasets;

import GeneratorPal.Managers.DatasetManager;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

public abstract class Dataset {
    //Data Fields
    private String name;
    private Path filePath;
    private HashMap<String, Object> activeElements;

    //Constructor - Used when loading a pre-created file.
    public Dataset(Path filePath) throws Exception {
        this.name = filePath.getFileName().toString();
        this.filePath = filePath;
        this.importFile(filePath);
    }
    //Constructor - Used when creating a new file.
    public Dataset(String name) throws Exception {
        this.name = name;
        this.filePath = null;
        this.activeElements = null;
    }

    //File Related Methods
    public void importFile(Path filePath) throws Exception {
        BufferedReader bufferedReader;
        String currentRow;
        int counter = 1;
        //Setting up the buffered reader
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(String.valueOf(filePath))));
            currentRow = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            throw new Exception("Unable to find file: \"" + filePath + "\"");}
        // Going through the opened file
        while (currentRow != null) {
            String[] elements = currentRow.split("\t");
            Object object = createObject(activeElements, elements);
            DatasetManager.getInstance().getUnfinishedObjects().remove(object);
            currentRow = bufferedReader.readLine();
        }
        // Closing the Buffered Reader
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new Exception("Unable to close file" + filePath);
        }
    }
    public void savedDataset(Path filePath) throws Exception{
        //https://www.journaldev.com/825/java-create-new-file#:~:text=File%20class%20can%20be%20used,This%20method%20also%20throws%20java.
        //this is unfinished
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        File file = chooser.getSelectedFile();
    }

    abstract public Object saveObjects();
    abstract public Object createObject(HashMap<String, Object> activeElements,String[] elements);

    ///Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Path getFilePath() {
        return filePath;
    }
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
    public HashMap<String, Object> getElements() {
        return activeElements;
    }
    public void setElements(HashMap<String, Object> activeElements) {
        this.activeElements = activeElements;
    }

    ///Add Methods
    public void addItem(String name, Object object){
        activeElements.put(name, object);
    }
}

