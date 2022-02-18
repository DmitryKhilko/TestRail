package tests;

import adapters.ProjectAdapter;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectPostPositiveResponse;
import models.response.ProjectResponse;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseAPITest;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectAPITest extends BaseAPITest {

    /*План CRUD API-тестов для объекта Projects TesRail:
    1. addProject(): создаем 3 проекта через @DataProvider(name = "addProjectData"), проверяем, что они созданы
    2. getOneProject(): получаем данные о 2-м проекте по id, проверяем, что полученные данные соответствуют данным, на основе которых создан проект (пункт 1)
    3. updateProject(): вносим изменения во 2-й проект, данные об изменениях получаем с помощью @DataProvider(name = "updateProjectData"), проверяем, что изменения сохранены
    4. getAllProject(): получаем данные обо всех созданных проектах, в них находим 2-й проект и проверяем, что изменения сохранены
    5. deleteProject(): удаляем созданные проекты
    Примечание: тесты зависимы, из одного теста в последующих передаются данные о проекте
    */

    //Объявляем переменные, в которые будут заносится значения полей 2-го проекта после его создания и после изменения
    public static Integer idWorkProject;
    public static String nameWorkProject;
    public static String announcementWorkProject;
    public static Boolean show_announcementWorkProject;
    public static Integer suite_modeWorkProject;
    public static Integer orderNumberWorkProject; //номер по порядку в List 2-го проекта

    //Данные для создания трех проектов
    @DataProvider(name = "addProjectData")
    public Object[][] addProjectData() {
        return new Object[][]{
                {"Проект 1", "Описание проекта: создание 1", true, 1},
                {"Проект 2", "Описание проекта: создание 2", true, 1},
                {"Проект 3", "Описание проекта: создание 3", true, 1}
        };
    }

    //Данные для изменения 2-го проекта
    @DataProvider(name = "updateProjectData")
    public Object[][] updateProjectData() {
        return new Object[][]{
                {"Проект 2_обновлено", "Описание проекта: обновление", false}
        };
    }

    @Description("Производится создание 3-х проектов на основе передаеваемых данных из @DataProvider и провекра создания данных проектов")
    @Test(description = "API-тест: добавление проекта", priority = 1, dataProvider = "addProjectData")
    public void addProject(ITestContext context, String name, String announcement, Boolean show_announcement, Integer suite_mode) {
        log.debug("Тест " + context.getAttribute("testName") + ": создать requestBody для передачи его в POST-запрос");
        Project requestBody = Project.builder()
                .name(name) //здесь и ниже подставлены значения, определенные в аннотации @DataProvider(name = "addProjectData")
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер POST-запрос на создание проекта '" + name + "'");
        ProjectPostPositiveResponse actual = new ProjectAdapter().postAddProject(requestBody, 200, "add", "");
        log.debug("Тест " + context.getAttribute("testName") + ": полученные актуальные (записанные в БД) данные о созданном проекте '" + name + "' :" + actual.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": создать объект с перечнем ожидаемых данных по проекту для последующей их сверки с актуальными данными");
        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(actual.getId())
                .name(name)
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_mode)
                .build();
        log.debug("Тест " + context.getAttribute("testName") + ": ожидаемые данные для проекта '" + name + "' -" + expected.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": присвоить переменным ожидаемые значения полей проекта для последующих тестов по поиску, редактированию проекта");
        if (expected.getName().equals("Проект 2")) {
            idWorkProject = actual.getId();
            nameWorkProject = expected.getName();
            announcementWorkProject = expected.getAnnouncement();
            show_announcementWorkProject = expected.getShow_announcement();
            suite_modeWorkProject = expected.getSuite_mode();
            orderNumberWorkProject = 1;
        }

        log.debug("Тест " + context.getAttribute("testName") + ": сравнить актуальные (записанные в БД) значения полей проекта с ожидаемыми значениями");
        assertEquals(actual, expected);
    }

    @Description("Производится получение данных о 2-м проекте по id и проверка того, что полученные данные соответствуют данным, на основе которых был создан проект")
    @Test(description = "API-тест: получение данных об одном проекте", priority = 2)
    public void getOneProject(ITestContext context) {

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер GET-запрос на получение данных о проекте с id " + idWorkProject);
        ProjectResponse actual = new ProjectAdapter().getOneProject(200, Integer.toString(idWorkProject));
        log.debug("Тест " + context.getAttribute("testName") + ": полученные актуальные (записанные в БД) данные о проекте с id " + idWorkProject + " -" + actual.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": создать объект с перечнем ожидаемых данных по проекту для последующей их сверки с актуальными данными");
        ProjectResponse expected = ProjectResponse.builder()
                .id(idWorkProject)
                .name(nameWorkProject)
                .announcement(announcementWorkProject)
                .show_announcement(show_announcementWorkProject)
                .suite_mode(suite_modeWorkProject)
                .build();
        log.debug("Тест " + context.getAttribute("testName") + ": ожидаемые данные для проекта c id " + idWorkProject + " -" + expected.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": сравнить актуальные (записанные в БД) значения полей проекта с ожидаемыми значениями");
        assertEquals(actual, expected);
    }

    @Description("Производится внесение изменений во 2-й проект на основе передаеваемых данных из @DataProvider и провекра сохранения изменений проекта")
    @Test(description = "API-тест: изменение данных проекта", priority = 3, dataProvider = "updateProjectData")
    public void updateProject(ITestContext context, String name, String announcement, Boolean show_announcement) {

        log.debug("Тест " + context.getAttribute("testName") + ": создать объект с перечнем данных для последующего изменения проекта c id " + idWorkProject);
        Project project = Project.builder()
                .name(name) //здесь и ниже подставлены значения, определенные в аннотации @DataProvider(name = "updateProjectData")
                .announcement(announcement)
                .show_announcement(show_announcement)
                .build();

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер POST-запрос на изменение данных проекте с id " + idWorkProject);
        ProjectPostPositiveResponse actual = new ProjectAdapter().postUpdateProject(project, 200, "update", Integer.toString(idWorkProject));
        log.debug("Тест " + context.getAttribute("testName") + ": полученные актуальные (записанные в БД) данные об измененном проекте с id " + idWorkProject + " - " + actual.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": создать объект с перечнем ожидаемых данных по проекту для последующей их сверки с актуальными данными");
        ProjectPostPositiveResponse expected = ProjectPostPositiveResponse.builder()
                .id(idWorkProject)
                .name(name)
                .announcement(announcement)
                .show_announcement(show_announcement)
                .suite_mode(suite_modeWorkProject)
                .build();
        log.debug("Тест " + context.getAttribute("testName") + ": ожидаемые данные для проекта '" + name + "' -" + expected.toString());

        log.debug("Тест " + context.getAttribute("testName") + ": присвоить переменным ожидаемые значения полей проекта для последующего его поиска и проверки правильности внесения изменений");
        idWorkProject = actual.getId();
        nameWorkProject = expected.getName();
        announcementWorkProject = expected.getAnnouncement();
        show_announcementWorkProject = expected.getShow_announcement();
        suite_modeWorkProject = expected.getSuite_mode();

        log.debug("Тест " + context.getAttribute("testName") + ": сравнить актуальные (записанные в БД) значения полей проекта с ожидаемыми значениями");
        assertEquals(actual, expected);
    }

    @Description("Производится получение данных обо всех созданных проектах, поиск в них 2-го проекта и проверка того, что изменения в проекте сохранены")
    @Test(description = "API-тест: получение данных обо всех проектах", priority = 4)
    public void getAllProject(ITestContext context) {

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер GET-запрос на получение данных обо всех созданных проектах");
        ProjectGetAllPositiveResponse actual = new ProjectAdapter().getAllProject(200);
        log.debug("Тест " + context.getAttribute("testName") + ": полученные актуальные (записанные в БД) данные обо всех созданных проектах -" + actual.getProjects());
        log.info(actual.getProjects());

        //Сравнение данных 2-го проекта, полученных из БД с ожидаемыми значениями (данными об изменении проекта с предыдущего теста)
        log.debug("Тест " + context.getAttribute("testName") + "сравнить актуальные (записанные в БД) данные полей 2-го проекта с ожидаемыми значениями (данными об изменении проекта с предыдущего теста)");
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getId(), idWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getName(), nameWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getAnnouncement(), announcementWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getShow_announcement(), show_announcementWorkProject);
        assertEquals(new ArrayList<>(actual.getProjects()).get(orderNumberWorkProject).getSuite_mode(), suite_modeWorkProject);
        //assertEquals(actual.getProjects().stream().collect(Collectors.toList()).get(orderNumberWorkProject).getSuite_mode(),suite_modeWorkProject);
    }

    @Description("Производится удаление всех созданных проектов")
    @Test(description = "API-тест: удаление созданных проектов", priority = 5)
    public void deleteProject(ITestContext context) {

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер POST-запрос на удаление проекта с id " + (idWorkProject - 1));
        int actualCode1 = new ProjectAdapter().postOneProjectDelete(200, Integer.toString(idWorkProject - 1));
        log.debug("Тест " + context.getAttribute("testName") + ": полученный от сервера код ответа - " + actualCode1);

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер POST-запрос на удаление проекта с id " + idWorkProject);
        int actualCode2 = new ProjectAdapter().postOneProjectDelete(200, Integer.toString(idWorkProject));
        log.debug("Тест " + context.getAttribute("testName") + ": полученный от сервера код ответа - " + actualCode2);

        log.debug("Тест " + context.getAttribute("testName") + ": создать и отправить на сервер POST-запрос на удаление проекта с id " + (idWorkProject + 1));
        int actualCode3 = new ProjectAdapter().postOneProjectDelete(200, Integer.toString(idWorkProject + 1));
        log.debug("Тест " + context.getAttribute("testName") + ": полученный от сервера код ответа - " + actualCode3);
    }

//    @Description("Производится удаление второго созданного проекта")
//    @Test (description = "API-тест: удаление проекта", priority = 5)
//    public void deleteProject2(ITestContext context){
//        int actualCode = new ProjectAdapter().postOneProjectDelete(200,Integer.toString(idWorkProject));
//        log.info(actualCode);
//    }
//
//    @Description("Производится удаление третьего созданного проекта")
//    @Test (description = "API-тест: удаление проекта", priority = 5)
//    public void deleteProject3(ITestContext context){
//        int actualCode = new ProjectAdapter().postOneProjectDelete(200,Integer.toString(idWorkProject+1));
//        log.info(actualCode);
//    }

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
