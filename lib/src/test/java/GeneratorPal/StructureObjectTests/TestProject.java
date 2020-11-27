package GeneratorPalTests.StructureObjectTests;

import main.StructureObjects.AssetPack;
import main.StructureObjects.FrameWork;
import main.StructureObjects.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {

    @Test
    public void testProjectConstructor() {
        Project example_Project = FrameWork.createProject("Example Project");
        assertEquals("Example Project",example_Project.getName());
    }

    @Test
    public void testCreateSelector() {
        Project example_Project = FrameWork.createProject("Example Project");
        AssetPack example_AssetPack = example_Project.createAssetPack("Example AssetPack");
        assertEquals("TestProjectName",example_Project.createSelector("Example Selector", example_AssetPack));
    }

    @Test
    public void testImportSelector() {
    }

    @Test
    public void testCreateGenerator() {
    }

    @Test
    public void testImportGenerator() {
    }

    @Test
    public void testCreateAssetPack() {
    }

    @Test
    public void testImportAssetPack() {
    }

    @Test
    public void testCreateTag() {
    }

    @Test
    public void testImportTag() {
    }

    @Test
    public void testCreateVariable() {
    }

    @Test
    public void testImportVariable() {
    }

    @Test
    public void testCreateTagGroup() {
    }

    @Test
    public void testImportTagGroup() {
    }

    @Test
    public void testCreateOption() {
    }

    @Test
    public void testImportOption() {
    }
}
