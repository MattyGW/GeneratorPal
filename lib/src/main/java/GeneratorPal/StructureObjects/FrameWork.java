package GeneratorPal.StructureObjects;


import java.io.File;
import java.util.ArrayList;

public class FrameWork {
    //Optional fields
    private static ArrayList<Project> projects;

    //Primary Methods
    public static Project createProject(String name) {return null;}
    public static Project importProject(File file) {return null;}

    //Secondary Methods
    ///Getters and Setters
    public static ArrayList<Project> getProjects() {
        return projects;
    }
    public static void setProjects(ArrayList<Project> projects) {
        FrameWork.projects = projects;
    }


//    //Old Stuff
//    //Iteration Mark
//
//    public File getFile(){
//        final JFileChooser fc = new JFileChooser();
//        return fc.getSelectedFile();
//    }
//
//    public Project newProject(String name){
//        //This creates a new project object and records a pointer in main object
//        Project project = new Project(name);
//        projects.add(project);
//        return project;
//    }
//
//    public void importProject(File file) throws Exception {
//        //Create Buffered Reader
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        //Record First Line
//        String[] line = br.readLine().split("\t");
//        //Create Project
//        Project project = new Project(line[0]);
//        //Create AssetPacks
//        for(int i = 1; i < line.length; i++){
//            project.newAssetPack(line[i]);
//        }
//        //Create Hashset
//        HashSet<String[]> incompleteObjects = new HashSet<>();
//        //Unpack Each Line
//        line = br.readLine().split("\t");
//        while(line[0] != null){
//            if(!project.importData(line)){
//                incompleteObjects.add(line);
//            }
//            line = br.readLine().split("\t");
//        }
//        //Finish incompleteObjects
//        Iterator<String[]> it = incompleteObjects.iterator();
//        while(it.hasNext()){
//            String[] currentLine = (String[])it.next();
//            if(project.importData(currentLine)){
//                incompleteObjects.remove(currentLine);
//            }
//        }
//        //Check Everything is complete
//        if(!incompleteObjects.isEmpty()){
//            throw new Exception("Not All Objects Completed");
//        }
//    }
}
