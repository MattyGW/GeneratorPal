package GeneratorPal.Datasets;

import GeneratorPal.Managers.DatasetManager;
import GeneratorPal.Objects.DataObjects.Item;

import java.nio.file.Path;
import java.util.HashMap;

public class ItemDataset extends Dataset {
    /*itemName, rules, description, tagName1,..., tagNameN*/
    public ItemDataset(Path filePath) throws Exception {
        super(filePath);
    }

    public Object createObject(HashMap<String, Object> activeElements, String[] elements){
        Item item = new Item(elements[0]);
        activeElements.put(item.getName(),item);
        item.setRules(elements[1]);
        item.setDescription(elements[2]);
        item.setItemDataset(this);
        for (int x = 3; x <= elements.length; x ++){
            DatasetManager.getInstance().linkObjects(item, elements[x]);
        }
        return item;
    }
}


//    //References
//    private AssetManager assetManager;
//    private StyleManager styleManager;
//    private InterfaceManager interfaceManager;
//    //Data tracked by ItemDataset
//    private String name;
//    private ArrayList<Tag> Tags;
//    private ArrayList<Item> items;
//
//    public ItemDataset(String name, File file) throws Exception {
//        this.assetManager = AssetManager.getAssetManager();
//        this.styleManager = StyleManager.getStyleManager();
//        this.interfaceManager = InterfaceManager.getInterfaceManager();
//        this.name = name;
//        System.out.println("Constructing Item Data Set: " + name);
//        this.Tags = new ArrayList<Tag>();
//        this.items = new ArrayList<Item>();
//        //Data for tag groups
//        Integer groupCounter = 0;
//        ArrayList<HashMap<String, Tag>> allTagGroups = new ArrayList<>();
//        ///Importing data from a tsv
//        BufferedReader bufferedReader;
//        String currentRow = null;
//        int counter = 1;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(file));
//            currentRow = bufferedReader.readLine();
//        } catch (FileNotFoundException e) {
//            throw new Exception("Method: loadItemTypesTSV \n - Unable to find file: \"" + file + "\"");}
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
//
//    public void fillSiblingCategories(ArrayList<HashMap<String, Tag>> allTagGroups){
//        System.out.println("Filling Sibling Categories");
//        System.out.println(allTagGroups);
//        for (HashMap<String,Tag> tagGroup: allTagGroups){
//            System.out.println(tagGroup);
//            Iterator iterator = tagGroup.entrySet().iterator();
//            ArrayList<Tag> tagGroupAsArray = new ArrayList<>();
//            while (iterator.hasNext()) {
//                Map.Entry tagEntry = (Map.Entry) iterator.next();
//                tagGroupAsArray.add((Tag)tagEntry.getValue());
//            }
//            iterator = tagGroup.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry tagEntry = (Map.Entry) iterator.next();
//                ((Tag)tagEntry.getValue()).setSiblingCategories((ArrayList) tagGroupAsArray.clone());
//                ((Tag)tagEntry.getValue()).getSiblingCategories().remove((Tag)tagEntry.getValue());
//            }
//        }
//    }
//
//    ///Getters & Setters
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public ArrayList<Tag> getItemCategories() {
//        return itemCategories;
//    }
//    public void setItemTags(ArrayList<Tag> tags) {
//        this.itemCategories = tags;
//    }
//    public ArrayList<Item> getItems() {
//        return items;
//    }
//    public void setItems(ArrayList<Item> items) {
//        this.items = items;
//    }
//
//    ///Add Methods
//    public void addItem(Item item){
//        items.add(item);
//    }
//    public void addTag(Tag tag){
//        itemCategories.add(tag);
//    }
//
//    ///Misc Methods
//    public Tag cautionedTagCreation(String tagName){
//        for (Tag tag : itemCategories){
//            if (tag.getName().equals(tagName)){
//                return tag;
//            }
//        }
//        Tag tag = new Tag(tagName,this);
//        this.addTag(tag);
//        return tag;
//    }
//
//    ///Random Actor
//    public Item getRandomItem() throws Exception {
//        return getRandomTag(this.getItems(), null, this.getItemCategories());
//    }
//
//    public Item getRandomTag(ArrayList<Item> allItems, ArrayList<Tag> mustCategories, ArrayList<Tag> remainingCategories) throws Exception {
//        //Step One (Setting up the total weight of the rePrimarying Categories)
//        int currentWeight = 0;
//        for (Tag tag: remainingCategories){
//            currentWeight += tag.getWeight();
//        }
//        //Step Two (Select create random number based on currentWeight)
//        Random random = new Random();
//        int randomNumber = random.nextInt(currentWeight);
//        //Step Four (Select a random tag)
//        for (Tag tag : remainingCategories){
//            currentWeight =- tag.getWeight();
//            if (currentWeight > randomNumber){
//                mustCategories.add(tag);
//                return prepRaPrimaryingCategories(this.getItems(), mustCategories);
//            }
//            else if (currentWeight < 0 ){
//                throw new Exception("A tag wasn't chosen for generating a tag.");
//            }
//        }
//        throw new Exception("This should ever be thrown.");
//    }
//
//    public Item prepRaPrimaryingCategories(ArrayList<Item> allItems, ArrayList<Tag> mustCategories) throws Exception {
//        //Go through the items and remove items that don't have must categories
//        ArrayList<Item> refactoredItemList = allItems;
//        ArrayList<Tag> refactoredTagList = new ArrayList<Tag>();
//        for (Item item : allItems) {
//            for (Tag tag : mustCategories) {
//                if (!(item.getItemTag().contains(tag))) {
//                    refactoredItemList.remove(item);
//                    break;
//                }
//            }
//        }
//        //Go through the items and create a new tag list.
//        for (Item item : refactoredItemList) {
//            for (Tag tag : item.getItemTag()) {
//                if (!(refactoredTagList.contains(tag))) {
//                    refactoredTagList.add(tag);
//                }
//            }
//        }
//        //Go through the tag list removing the must tags
//        for (Tag tag : mustCategories) {
//            if (refactoredTagList.contains(tag)) {
//                refactoredTagList.remove(tag);
//            }
//        }
//        //Check if refactored Tag List is empty
//        if (refactoredTagList.isEmpty()) {
//            return generateRandomItem(refactoredItemList);
//        }
//        return getRandomTag(refactoredItemList, mustCategories, refactoredTagList);
//    }
//
//    public Item generateRandomItem(ArrayList<Item> items) throws Exception {
//        Random random = new Random();
//        int randomNumber = random.nextInt(items.size());
//        Iterator iterator = items.iterator();
//        int currentIndex = 0;
//        Element randomElement = null;
//        while(iterator.hasNext()){
//            randomElement = (Element) iterator.next();
//            if (currentIndex == randomNumber){
//                return (Item) randomElement;
//            }
//            currentIndex += 1;
//        }
//        throw new Exception("An item wasnt generated");
//    }

