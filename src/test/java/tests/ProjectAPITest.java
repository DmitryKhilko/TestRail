package tests;

import adapters.ProjectAdapter;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.Test;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectGetPositiveResponse;
import models.response.ProjectPostPositiveResponse;

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
        ProjectPostPositiveResponse actual = new ProjectAdapter().postProject(requestBody,200, "add", "");

        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
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
        ProjectPostPositiveResponse actual = new ProjectAdapter().postProject(project,200, "update", idProject);

        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(actual.getId()) //поставить реальные значения!!!!! через DataProvider
                .name(actual.getName())
                .announcement(actual.getAnnouncement())
                .show_announcement(actual.getShow_announcement())
                .suite_mode(actual.getSuite_mode())
                .build();

        log.info("expected :" + expected.toString());
        log.info("actual :" + actual.toString());

        assertEquals(actual,expected);
    }

    @Test
    public void getAllProject(){
        ProjectGetAllPositiveResponse actual = new ProjectAdapter().getAllProject(200);
        log.info(actual);
    }

    @Test
    public void getOneProject(){
        ProjectGetPositiveResponse actual = new ProjectAdapter().getOneProject(200, "46");
        //ProjectGetPositiveResponse expected =
        log.info(actual);
        //log.info(actual.getId());

    }


//    @Test
//    public void getProjectByRealNameAndNotEmptyTest() {
//        log.info("Search project by correct code and name with cases, suites and other.");
//        PositiveResponseStatus actual = new ProjectAdapter().getProjectWithCorrectCode(200, "DEMO");
//        PositiveResponseStatus expected = PositiveResponseStatus.builder()
//                .status(true)
//                .result(Result.builder()
//                        .title("Demo Project")
//                        .code("DEMO")
//                        .counts(Counts.builder()
//                                .cases(10)
//                                .suites(3)
//                                .milestones(2)
//                                .runs(Runs.builder()
//                                        .total(0)
//                                        .active(0)
//                                        .build())
//                                .defects(Defects.builder()
//                                        .total(0)
//                                        .open(0)
//                                        .build())
//                                .build())
//                        .build())
//                .build();
//
//        assertEquals(actual, expected);
//    }



}
