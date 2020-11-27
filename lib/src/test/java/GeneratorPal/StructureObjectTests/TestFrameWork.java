package GeneratorPalTests.StructureObjectTests;

import main.StructureObjects.FrameWork;
import main.StructureObjects.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFrameWork {


    @Test
    public void testImportProject() throws Exception {
        throw new Exception("Make a test file for me");
    }

    @Test
    public void testCreateProject() {
        Project project = FrameWork.createProject("TestProjectName");
        assertAll(
                //Check project is created plus correct name
                () -> assertEquals("TestProjectName",project.getName()),
                //Check project recorded in FrameWork
                () -> assertTrue(FrameWork.getProjects().contains(project))
         );
    }
}