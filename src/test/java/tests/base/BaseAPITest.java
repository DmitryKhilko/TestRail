package tests.base;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2 //аннотация для вставки в клласс команд логирования
public abstract class BaseAPITest {

    @BeforeMethod (description = "Стартовать тест")//Предусловие
    public void setUp(ITestContext context, ITestResult result) {
        log.info("Тест " + result.getMethod().getMethodName() + ": старт"); //команда лога, куда передается имя выполняемого теста
        context.setAttribute("testName", result.getMethod().getMethodName()); //передаем имя выполняемого теста в методы тестового фреймворка для наглядного формирования логов
    }

    @AfterMethod(alwaysRun = true, description = "Закончить тест") //Постусловие
    public void tearDown(ITestResult result) {
        log.info("Тест " + result.getMethod().getMethodName() + ": финиш"); //команда лога, куда передается имя выполняемого теста
    }
}