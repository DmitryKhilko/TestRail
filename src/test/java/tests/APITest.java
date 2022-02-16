package tests;

import adapters.ProjectAdapter;
import models.Project;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class APITest {
    @Test
    public void updateProject(){
        Project project = Project.builder()
                .name("Проект, созданный через API")
                .announcement("Мой первый успех!!!")
                .show_announcement(true)
                .suite_mode(1)
                .build();
        String actual = new ProjectAdapter().post(project,200, "add", "");
        assertEquals(actual,"");
    }
}
