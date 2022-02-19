package adapters;

import io.qameta.allure.Step;
import models.Project;
import models.response.ProjectAllPositiveResponse;
import models.response.ProjectNegativeResponse;
import models.response.ProjectPositiveResponse;

public class ProjectAdapter extends BaseAdapter {

    //Метод отправляет на сервер POST-запрос на создание проекта,
    // получает ответ сервера: значение Status Code, JSON с параметрами созданного проекта,
    // преобразует JSON в убобную для восприятия и анализа форму
    //body запроса на сервер, ответа с сервера смотри в Postman
    @Step("Получить c сервера ответ на отправленный POST запрос на создание проекта")
    public ProjectPositiveResponse postAddProject(Project requestBody, int expectedStatusCode){
        String response = postTemplateAddProject(gson.toJson(requestBody, Project.class), expectedStatusCode);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    //Попытка добавить проект с некорректными данными, например, пустым названием, несуществующим suite_mode (коректные: 1,2,3)
    //Метод отправляет на сервер POST-запрос на создание проекта, получает ответ сервера: значение Status Code (400)
    // и в случае пустого имени JSON ({"error": "Field :name is a required field."}) или в случае некорректного
    // suite_mode JSON ({"error": "Field :suite_mode is not a supported enum value (\"5\")."})
    //body запроса на сервер, ответа с сервера смотри в Postman
    @Step("Получить c сервера ответ на отправленный POST запрос на создание проекта с некорректными параметрами")
    public ProjectNegativeResponse postAddProjectNegative(Project requestBody, Integer expectedStatusCode){
        String response = postTemplateAddProject(gson.toJson(requestBody, Project.class), expectedStatusCode);
        return gson.fromJson(response, ProjectNegativeResponse.class);
    }

    @Step("Получить c сервера ответ на отправленный GET запрос на получение данных о проекте по его id")
    public ProjectPositiveResponse getOneProject(int expectedStatusCode, String idProject){
        String response = super.getProject(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    @Step("Получить c сервера ответ на отправленный GET запрос на получение данных обо всех созданных проектах")
    public ProjectAllPositiveResponse getAllProject(int expectedStatusCode){
        String response = super.getProjectAll(expectedStatusCode);
        return gson.fromJson(response, ProjectAllPositiveResponse.class);
    }

    @Step("Получить c сервера ответ на отправленный POST запрос на изменение проекта")
    public ProjectPositiveResponse postUpdateProject(Project requestBody, int expectedStatusCode, String idProject){
        String response = postTemplateUpdateProject(gson.toJson(requestBody, Project.class), expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    //В TestRail POST-запрос не возвращает какого-то ответа, кроме кода ответа (Status Code)
    //Метод отправляет на сервер POST-запрос на удаление проекта по его id,
    //возвращает c сервера значение Status Code (200)
    @Step("Получить c сервера ответ на отправленный POST запрос на удаление проекта")
    public int postProjectDelete(int expectedStatusCode, String idProject){
        return super.postProjectDelete(expectedStatusCode, idProject);
    }
    //Попытка удаления несуществующего проекта
    //Метод отправляет на сервер POST-запрос на удаление проекта по несуществующему id,
    //возвращает c сервера значение Status Code (400) и JSON ({"error": "Field :project_id is not a valid or accessible project."})
    @Step("Получить c сервера ответ на отправленный POST запрос на удаление проекта с некорректным id")
    public ProjectNegativeResponse postProjectDeleteNegative(int expectedStatusCode, String idProject) {
        String response = super.postProjectDeleteNegative(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectNegativeResponse.class);
    }
}
