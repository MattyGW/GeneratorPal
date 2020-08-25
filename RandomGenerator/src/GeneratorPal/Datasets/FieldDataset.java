package GeneratorPal.Datasets;

import GeneratorPal.Managers.DatasetManager;
import GeneratorPal.Objects.DataObjects.Field;

import java.nio.file.Path;
import java.util.HashMap;

public class FieldDataset extends Dataset {
    /*fieldName, description, tagName1, tagInteraction1, ..., tagNameN, tagInteractionN*/
    public FieldDataset(Path filePath) throws Exception {
        super(filePath);
    }

    @Override
    public Object saveObjects() {
        return null;
    }

    public Object createObject(HashMap<String, Object> activeElements,String[] elements) {
        Field field = new Field(elements[0]);
        activeElements.put(field.getName(),field);
        field.setFieldDataset(this);
        field.setDescription(elements[1]);
        for (int x = 2; x <= elements.length; x = x + 2) {
            DatasetManager.getInstance().linkObjects(field, elements[x], elements[x+1]);
        }
        return field;
    }
}