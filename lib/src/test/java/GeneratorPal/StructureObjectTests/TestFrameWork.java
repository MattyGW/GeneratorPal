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
        //Set-up Test Variables
        Project test_Project = FrameWork.createProject("testProject");
        //Run tests
        assertAll(
            //Check project is created plus correct name
            () -> assertEquals("TestProjectName",test_Project.getName()),
            //Check project recorded in FrameWork
            () -> assertEquals(true,FrameWork.getProjects().contains(test_Project))
        );
    }
}