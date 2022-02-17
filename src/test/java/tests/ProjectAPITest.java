package tests;

import adapters.ProjectAdapter;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectGetPositiveResponse;
import models.response.ProjectPostPositiveResponse;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectAPITest {

    public static String idProject;

    @DataProvider(name = "addProjectData")
    public Object[][] addProjectData(){
        return new Object[][] {
                {"Проверка работы создания проекта", "Описание проекта: создание", true, 1}
        };
    }

    @DataProvider(name = "updateProjectData")
    public Object[][] updateProjectData(){
        return new Object[][] {
                {"Проверка работы создания проекта_обновлено", "Описание проекта: обновление", false, 3}
        };
    }

    @Test (priority = 1, dataProvider = "addProjectData")
    public void addProject(String name, String announcement, Boolean show_announcement, Integer suite_mode){
        Project requestBody = Project.builder()
                .name(name) //здесь и ниже подставлены значения, определенные в аннотации @DataProvider(name = "addProjectData")
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();
        ProjectPostPositiveResponse actual = new ProjectAdapter().postAddProject(requestBody,200, "add", "");
        log.info("actual :" + actual.toString());

        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(actual.getId())
                .name(name)
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();
        log.info("expected :" + expected.toString());

        idProject = Integer.toString(actual.getId()); //выявляем id созданного проекта. Так как тип поля id - "Integer", а в другие тесты необходимо код проекта подставлять как строку, преобразуем код проекта в String

        assertEquals(actual,expected);
    }
    @Test (priority = 2, dataProvider = "updateProjectData")
    public void updateProject(String name, String announcement, Boolean show_announcement, Integer suite_mode){

        Project project = Project.builder()
                .name(name) //здесь и ниже подставлены значения, определенные в аннотации @DataProvider(name = "updateProjectData")
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();
        ProjectPostPositiveResponse actual = new ProjectAdapter().postUpdateProject(project,200, "update", idProject);
        log.info("actual :" + actual.toString());

        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(actual.getId())
                .name(name)
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();
        log.info("expected :" + expected.toString());

        assertEquals(actual,expected);
    }

    @Ignore
    @Test
    public void deleteProject(){
        ProjectPostPositiveResponse actual = new ProjectAdapter().postOneProjectDelete(200,"51");
        //log.info(actual);
    }

    @Ignore
    @Test
    public void getAllProject(){
        ProjectGetAllPositiveResponse actual = new ProjectAdapter().getAllProject(200);
        log.info(actual);
    }

    @Description("Вернуть информацию о проекте 'Example Project' при его наличии в базе данных")
    @Test
    public void getOneProject(){
        ProjectGetPositiveResponse actual = new ProjectAdapter().getOneProject(200, "49");
        log.info("actual :" + actual.toString());
        ProjectGetPositiveResponse expected = ProjectGetPositiveResponse.builder()
                .id(49)
                .name("Example Project_do not delete")
                .announcement("Example announcement")
                .show_announcement(true)
                .suite_mode(1)
                .is_completed(false)
                .default_role_id(null)
                .url("https://hdn.testrail.io/index.php?/projects/overview/49")
                .build();
        log.info("expected :" + expected.toString());

        assertEquals(actual,expected);
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
