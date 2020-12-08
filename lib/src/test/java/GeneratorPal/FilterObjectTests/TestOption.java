package GeneratorPal.FilterObjectTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import GeneratorPal.ActorObjects.Actor;
import GeneratorPal.StructureObjects.AssetPack;
import GeneratorPal.StructureObjects.FrameWork;
import GeneratorPal.StructureObjects.Project;
import GeneratorPal.FilterObjects.Option;

public class TestOption {
    //* Pass One Done

    @Test
    public void testIsActorOption() throws Exception {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        Actor test_Actor = test_Project.createGenerator("test_Actor", test_AssetPack);
        Option test_Option_1 = test_Project.createOption("test_Option_1", test_AssetPack);
        Option test_Option_2 = test_Project.createOption("test_Option_2", test_AssetPack);
        test_Option_2.setActor(test_Actor);
        //Run tests
        assertAll(
            //Check that Option 1 is not a Actor Option
            () -> assertEquals(false,test_Option_1.isActorOption()),
            //Check that Option 2 is a Actor Option
            () -> assertEquals(true,test_Option_2.isActorOption())
        ); 
    }
}
