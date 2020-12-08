package GeneratorPal.StructureObjectTests;

import GeneratorPal.ActorObjects.Generator;
import GeneratorPal.ActorObjects.Selector;
import GeneratorPal.FilterObjects.Option;
import GeneratorPal.FilterObjects.Tag;
import GeneratorPal.FilterObjects.TagGroup;
import GeneratorPal.FilterObjects.Variable;
import GeneratorPal.StructureObjects.AssetPack;
import GeneratorPal.StructureObjects.FrameWork;
import GeneratorPal.StructureObjects.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {

    @Test
    public void testCreateSelector() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        //Run the test method
        Object test_Object = test_Project.createSelector("testSelector", test_AssetPack);
        //Run tests
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getActors().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getActors().contains(test_Object)),
            //Check correct class
            () -> assertEquals(true,test_Object.getClass().equals(Selector.class)),
            //Check name assigned correctly
            () -> assertEquals(true,((Selector)test_Object).getName()=="testSelector")
        );
    }
    
    @Test
    public void testImportSelector() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateGenerator() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        //Run the test method
        Object test_Object = test_Project.createGenerator("testGenerator", test_AssetPack);
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getActors().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getActors().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(Generator.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((Generator)test_Object).getName()=="testGenerator")
        );
    }

    @Test
    public void testImportGenerator() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateAssetPack() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        //Run the test method
        Object test_Object = test_Project.createAssetPack("testAssetPack");
        assertAll(
            //Check recorded with pointer
            () -> assertEquals(true,test_Project.getAssetPacks().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(AssetPack.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((AssetPack)test_Object).getName()=="testAssetPack")
        );
    }

    @Test
    public void testImportAssetPack() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateTag() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        TagGroup test_TagGroup = test_Project.createTagGroup("testTagGroup", "testTagGroupDescription", test_AssetPack);
        //Run the test method
        Object test_Object = test_Project.createTag("testTag", "testTagDescription", test_AssetPack, test_TagGroup);
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getTags().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getTags().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(Tag.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((Tag)test_Object).getName()=="testTag")
        );
    }

    @Test
    public void testImportTag() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateVariable() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        //Run the test method
        Object test_Object = test_Project.createVariable("testVariable", "testVariableDescription", test_AssetPack);
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getVariables().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getVariables().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(Variable.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((Variable)test_Object).getName()=="testVariable")
        );
    }

    @Test
    public void testImportVariable() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateTagGroup() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        //Run the test method
        Object test_Object = test_Project.createTagGroup("testTagGroup", "testTagGroupDescription", test_AssetPack);
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getTagGroups().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getTagGroups().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(TagGroup.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((TagGroup)test_Object).getName()=="testTagGroup")
        );
    }

    @Test
    public void testImportTagGroup() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }

    @Test
    public void testCreateOption() {
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        AssetPack test_AssetPack = test_Project.createAssetPack("testAssetPack");
        //Run the test method
        Object test_Object = test_Project.createOption("testOption", "testOptionDescription", test_AssetPack);
        assertAll(
            //Check recorded with pointer in test_Project
            () -> assertEquals(true,test_Project.getOptions().contains(test_Object)),
            //Check recorded with pointer in test_AssetPack
            () -> assertEquals(true,test_AssetPack.getOptions().contains(test_Object)),
            //Check correct Class
            () -> assertEquals(true,test_Object.getClass().equals(Option.class)),
            //Check name assigned correcrly
            () -> assertEquals(true,((Option)test_Object).getName()=="testOption")
        );
    }

    @Test
    public void testImportOption() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
    }
}
