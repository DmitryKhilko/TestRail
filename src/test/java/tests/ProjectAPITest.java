package tests;

import adapters.ProjectAdapter;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectPostPositiveResponse;
import models.response.ProjectResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectAPITest {
    //План проверки API:
    //
    // Нужно подключить фейкер
    //
    //1. Создаем 3 проекта (через @DataProvider(name = "addProjectData") (id проектов заносим в List) https://vertex-academy.com/tutorials/ru/list-java-primer/
    //2. Делаем просмотр 2-го проекта по id (из List)
    //3. Делаем изменение 2-го проекта
    //4. Делаем просмотр всех проектов, находим по id тот проект, который изменился и анализируем, правильно ли он изменился (делаем перебор в объекте List<> в ProjectGetAllPositiveResponse)
    //5. Удаляем все проекты
 

    public static Integer idWorkProject; //инициализируем переменную, в которую будем передавать id проекта, с которым будем производить иные операции (обновление, удаление)
    public static String nameWorkProject;
    public static String announcementWorkProject;
    public static Boolean show_announcementWorkProject;
    public static Integer suite_modeWorkProject;
    public static Integer orderNumberWorkProject; //какой проект будет подвергаться изменениям и просмотру (0,1,2)

    @DataProvider(name = "addProjectData")
    public Object[][] addProjectData(){
        return new Object[][] {
                {"Проект 1", "Описание проекта: создание 1", true, 1},
                {"Проект 2", "Описание проекта: создание 2", true, 1},
                {"Проект 3", "Описание проекта: создание 3", true, 1}
        };
    }

    @DataProvider(name = "updateProjectData")
    public Object[][] updateProjectData(){
        return new Object[][] {
                {"Проект 2_обновлено", "Описание проекта: обновление", false}
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

        //Запоминаем фактические значения полей проекта для последующего его поиска, редактирования
        if (expected.getName().equals("Проект 2")) {
            idWorkProject = actual.getId();
            nameWorkProject = expected.getName();
            announcementWorkProject = expected.getAnnouncement();
            show_announcementWorkProject = expected.getShow_announcement();
            suite_modeWorkProject = expected.getSuite_mode();
            orderNumberWorkProject = 1;
        }

        assertEquals(actual,expected);
    }

    @Test (priority = 2)
    public void getOneProject(){
        ProjectResponse actual = new ProjectAdapter().getOneProject(200, Integer.toString(idWorkProject));
        log.info("actual :" + actual.toString());
        ProjectResponse expected = ProjectResponse.builder()
                .id(idWorkProject)
                .name(nameWorkProject)
                .announcement(announcementWorkProject)
                .show_announcement(show_announcementWorkProject)
                .suite_mode(suite_modeWorkProject)
                .build();
        log.info("expected :" + expected.toString());

        assertEquals(actual,expected);
    }


    @Test (priority = 3, dataProvider = "updateProjectData")
    public void updateProject(String name, String announcement, Boolean show_announcement){

        Project project = Project.builder()
                .name(name) //здесь и ниже подставлены значения, определенные в аннотации @DataProvider(name = "updateProjectData")
                .announcement(announcement)
                .show_announcement(show_announcement)
                .build();
        ProjectPostPositiveResponse actual = new ProjectAdapter().postUpdateProject(project,200, "update", Integer.toString(idWorkProject));
        log.info("actual :" + actual.toString());

        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(idWorkProject)
                .name(name)
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_modeWorkProject)
                .build();
        log.info("expected :" + expected.toString());

        //Запоминаем фактические значения полей проекта для последующего его поиска
        idWorkProject = actual.getId();
        nameWorkProject = expected.getName();
        announcementWorkProject = expected.getAnnouncement();
        show_announcementWorkProject = expected.getShow_announcement();
        suite_modeWorkProject = expected.getSuite_mode();

        assertEquals(actual,expected);
    }

    @Test (priority = 4)
    public void getAllProject(){
        ProjectGetAllPositiveResponse actual = new ProjectAdapter().getAllProject(200);
        log.info(actual.getProjects());

        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getId(),idWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getName(),nameWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getAnnouncement(),announcementWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getShow_announcement(),show_announcementWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getSuite_mode(),suite_modeWorkProject);
        //assertEquals(actual.getProjects().stream().collect(Collectors.toList()).get(orderNumberWorkProject).getSuite_mode(),suite_modeWorkProject);
    }

    //@Ignore
    @Test (priority = 5)
    public void deleteProject1(){
        int actualCode = new ProjectAdapter().postOneProjectDelete(200,Integer.toString(idWorkProject-1));
        log.info(actualCode);
    }

    //@Ignore
    @Test (priority = 5)
    public void deleteProject2(){
        int actualCode = new ProjectAdapter().postOneProjectDelete(200,Integer.toString(idWorkProject));
        log.info(actualCode);
    }

    //@Ignore
    @Test (priority = 5)
    public void deleteProject3(){
        int actualCode = new ProjectAdapter().postOneProjectDelete(200,Integer.toString(idWorkProject+1));
        log.info(actualCode);
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
