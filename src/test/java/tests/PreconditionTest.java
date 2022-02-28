package tests;

import adapters.ProjectAdapter;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.response.ProjectPositiveResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import tests.base.BaseAPITest;

import static org.testng.Assert.assertEquals;

@Log4j2
//Создаем проект-заглушку
public class PreconditionTest extends BaseAPITest {

    @Description("Производится создание проекта-заглушки из-за интрфейсных различий в создании первого и последующего проектов")
    @Test(priority = 1, description = "Проект-заглушка")
    public void addAPIProject(ITestContext context) {
        Project requestBody = Project.builder()
                .name("Проект-заглушка")
                .announcement("Описание")
                .show_announcement(true)
                .suite_mode(1)
                .build();

        ProjectPositiveResponse actual = new ProjectAdapter().postAddProject(requestBody, 200);
        ProjectPositiveResponse expected = ProjectPositiveResponse.builder()
                .id(actual.getId())
                .name("Проект-заглушка")
                .announcement("Описание")
                .show_announcement(true)
                .suite_mode(1)
                .build();
        assertEquals(actual, expected);
    }
}