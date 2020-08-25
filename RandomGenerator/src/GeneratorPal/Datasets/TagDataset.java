package GeneratorPal.Datasets;

import GeneratorPal.Managers.DatasetManager;
import GeneratorPal.Objects.DataObjects.Tag;

import java.nio.file.Path;
import java.util.HashMap;

public class TagDataset extends Dataset {
    public TagDataset(Path filePath) throws Exception {
        super(filePath);
    }

    public Object createObject(HashMap<String, Object> activeElements, String[] elements){
        Tag tag = new Tag(elements[0]);
        activeElements.put(tag.getName(),tag);
        tag.setTagDataset(this);
        for (int x = 1; x <= elements.length - 2; x ++){
            DatasetManager.getInstance().linkObjects(tag, elements[x]);
        }
        return tag;
    }
}

//
//    //Data tracked by
//    private String name;
//    private HashMap<String,Tag> tags;
//    private HashMap<String,Integer> tagWeights;
//    private HashMap<Tag, String[]> tagRequirements;
//
//    // have the import method call tagDataSet methods to finalize its creation. eg. set things up
//
//    public TagDataset(String name, File file) throws Exception {
//        this.name = name;
//        System.out.println("Constructing Tag Data Set: " + name);
//        this.tags = new HashMap<String,Tag>;
//        this.tagWeights = new HashMap<String,Integer>();
//        this.tagRequirements = new HashMap<Tag, String[]>();
//    }
//
//    private static HashMap<String, Tag> extractTSVData(File file) throws IOException {
//        System.out.println(" - Extracting Tag Data from: " + file.getCanonicalPath());
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        String currentRow = bufferedReader.readLine();
//        HashMap<String, Tag> tags = new HashMap<>();
//        HashMap<Tag, String[]> tagRequirements = new HashMap<>();
//        while (currentRow != null){
//            String[] elements = currentRow.split("\t");
//            Tag tag = new Tag(elements[0],this, getTagGroup(elements[1]),elements[2]);
//            tagRequirements.put(tag,elements);
//        }
//        Iterator iterator = tagRequirements.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry tagRequirement = (Map.Entry) iterator.next();
//            for (String tagName: (String[]) tagRequirement.getValue()){
//                ((Tag) tagRequirement.getKey()).
//            }
//            return tags;
//        }
//
//
//        //Data for tag groups
//        Integer groupCounter = 0;
//        ArrayList<HashMap<String, Tag>> allTagGroups = new ArrayList<>();
//        ///Importing data from a tsv
//        int counter = 1;
//        // Going through the open file
//        while (currentRow != null) {
//            //Other stuff
//            String[] elements = currentRow.split("\t");
//            int rowLength = elements.length;
//            if (rowLength < 3){
//                throw new Exception("The TSV file has less then 3 Columns it needs a name, rules and description roll");
//            }
//            else if (rowLength == 3){
//                Tag tag = new Tag("default", this);
//                Item item = new Item(elements[0], elements[1], elements[2], this);
//                tag.addItem(item);
//                item.addTag(tag);
//                this.addItem(item);
//                this.addTag(tag);
//            }
//            else { // happens when rowlength is > 3
//                Item item = new Item(elements[0], elements[rowLength-2], elements[rowLength-1], this);
//                this.addItem(item);
//                for (int i = 1; i < rowLength-2; i++){
//                    Tag tag = cautionedTagCreation(elements[i]);
//                    System.out.println("There is " + allTagGroups.size() + " tag groups, the row length is "+ rowLength);
//                    if (allTagGroups.size() < (rowLength-3)){
//                        System.out.println(" - Adding another tag group.");
//                        HashMap<String,Tag> tagGroup = new HashMap<>();
//                        allTagGroups.add(tagGroup);
//                    }
//                    //Stuff for tag groups
//                    allTagGroups.get(i-1).put(tag.getName(),tag);
//                    tag.addItem(item);
//                    item.addTag(tag);
//                }
//            }
//            currentRow = bufferedReader.readLine();
//        }
//        // Closing the Buffered Reader
//        try {
//            bufferedReader.close();
//        } catch (IOException e) {
//            System.out.println("Unable to close file" + file);
//            throw new Exception("Unable to close file" + file);
//        }
//        fillSiblingCategories(allTagGroups);
//    }
//}
