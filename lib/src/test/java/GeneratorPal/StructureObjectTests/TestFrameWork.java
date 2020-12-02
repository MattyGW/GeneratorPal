package GeneratorPal.StructureObjectTests;

import GeneratorPal.StructureObjects.FrameWork;
import GeneratorPal.StructureObjects.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFrameWork {
    //* Pass One Done

    @Test
    public void testImportProject() throws Exception {
        throw new Exception("Make a test file for me");
        //! Create a test file
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