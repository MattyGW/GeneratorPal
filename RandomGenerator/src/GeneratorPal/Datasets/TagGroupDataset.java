package GeneratorPal.Datasets;

import GeneratorPal.Managers.DatasetManager;
import GeneratorPal.Objects.DataObjects.TagGroup;

import java.nio.file.Path;
import java.util.HashMap;

public class TagGroupDataset extends Dataset {
    /*tagGroupName, description, tagName1,..., tagNameN*/
    public TagGroupDataset(Path filePath) throws Exception {
        super(filePath);
    }

    public Object createObject(HashMap<String, Object> activeElements, String[] elements){
        TagGroup tagGroup = new TagGroup(elements[0]);
        activeElements.put(tagGroup.getName(),tagGroup);
        tagGroup.setTagGroupDataset(this);
        for (int x = 1; x <= elements.length; x ++){
            DatasetManager.getInstance().linkObjects(tagGroup, elements[x]);
        }
        return tagGroup;
    }
}
