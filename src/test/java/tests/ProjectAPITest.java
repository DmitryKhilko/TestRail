package tests;

import adapters.ProjectAdapter;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.Test;
import response.ProjectPositiveResponse;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectAPITest {

    public static String idProject;

    @Test (priority = 1)
    public void addProject(){
        Project requestBody = Project.builder()
                .name("Проверка работы создания проекта")
                .announcement("Описание проекта: создание")
                .show_announcement(true)
                .suite_mode(1)
                .build();
        ProjectPositiveResponse actual = new ProjectAdapter().postProject(requestBody,200, "add", "");

        ProjectPositiveResponse expected = ProjectPositiveResponse.builder()
                .id(actual.getId())
                .name(actual.getName())
                .announcement(actual.getAnnouncement())
                .show_announcement(actual.getShow_announcement())
                .suite_mode(actual.getSuite_mode())
                .build();

        idProject = Integer.toString(actual.getId()); //выявляем id созданного проекта. Так как тип поля id - "Integer", а в другие тесты необходимо код проекта подставлять как строку, преобразуем код проекта в String

        assertEquals(actual,expected);
    }
    @Test (priority = 2)
    public void updateProject(){

        Project project = Project.builder()
                .name("Проверка работы создания проекта_")
                .announcement("Описание проекта: создание_")
                .show_announcement(false)
                .suite_mode(2)
                .build();
        ProjectPositiveResponse actual = new ProjectAdapter().postProject(project,200, "update", idProject);

        ProjectPositiveResponse expected = ProjectPositiveResponse.builder()
                .id(actual.getId())
                .name(actual.getName())
                .announcement(actual.getAnnouncement())
                .show_announcement(actual.getShow_announcement())
                .suite_mode(actual.getSuite_mode())
                .build();

        log.info("expected :" + expected.toString());
        log.info("actual :" + actual.toString());


        assertEquals(actual,expected);
    }
}
