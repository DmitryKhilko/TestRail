package adapters;

import io.qameta.allure.Step;
import models.Project;
import models.response.ProjectAllPositiveResponse;
import models.response.ProjectPositiveResponse;

public class ProjectAdapter extends BaseAdapter {

    @Step("Проверка 2")
    //Метод отправляет на сервер POST-запрос на создание проекта,
    // получает ответ сервера: значение Status Code, JSON с параметрами созданного проекта,
    // преобразует JSON в убобную для восприятия и анализа форму
    public ProjectPositiveResponse postAddProject(Project requestBody, Integer expectedStatusCode){
        String response = postTemplateAddProject(gson.toJson(requestBody, Project.class), expectedStatusCode);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    public ProjectPositiveResponse postUpdateProject(Project requestBody, Integer expectedStatusCode, String idProject){
        String response = postTemplateUpdateProject(gson.toJson(requestBody, Project.class), expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    //В TestRail POST-запрос не возвращает какого-то ответа, кроме кода ответа (Status Code)
    //Метод отправляет на сервер POST-запрос на удаление проекта по его id,
    //возвращает c сервера значение Status Code
    public int postOneProjectDelete(Integer expectedStatusCode, String idProject){
        return super.postProjectDelete(expectedStatusCode, idProject);
    }

    public ProjectPositiveResponse getOneProject(Integer expectedStatusCode, String idProject){
        String response = super.getProject(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }

    public ProjectAllPositiveResponse getAllProject(Integer expectedStatusCode){
        String response = super.getProjectAll(expectedStatusCode);
        return gson.fromJson(response, ProjectAllPositiveResponse.class);
    }
}
