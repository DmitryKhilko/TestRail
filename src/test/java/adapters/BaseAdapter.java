package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.given;
import static pages.BasePage.BASE_URL;

@Log4j2
public class BaseAdapter {
    Gson gson = new Gson(); //вынесли в BaseAdapter, так как данная библиотека будет использоваться во множестве адаптеров, например, ProjectAdapter. То есть, все адаптеры, которые наследуются от текущего класса, смогут использовать данную библиотеку

    //Шаблон POST-запроса на создание и изменение объекта Project в TestRail (POST-запросы на создание, изменение имеют одинаковую структуру)
    @Step("Проверка1")
    public String postTemplateAddProject(String body, int expectedStatusCode){
        return
                given()
                        //.log().all() //выводит всю информацию о запросе, в штатном режиме лучше закоментировать
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz") //авторизация по логинуи паролю (этот код достал из Postman)
                        //.header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth") //в TestRail досточно авторизации по логину и паролю, хотя возможен доступ и по токену
                        .header("Content-Type", "application/json") //формат содержимого (отправка)
                        .header("Accept" , "application/json") //формат приема
                        .body(body) //тело запроса: перечисление отправляемых параметров и их значений
                .when() //собственно сам запрос на создание(изменение) проекта
                        .post(BASE_URL +"/api/v2/add_project")
                .then()
                        //.log().all() //выводит всю информацию об ответе, в штатном режиме лучше закоментировать
                        .statusCode(expectedStatusCode) //возвращает код ответа сервера
                        .extract().body().asString(); //возвращает тело ответа, если оно предусмотренно программистами
    }

    //Шаблон POST-запроса на создание и изменение объекта Project в TestRail (POST-запросы на создание, изменение имеют одинаковую структуру)
    @Step("Проверка1")
    public String postTemplateUpdateProject(String body, int expectedStatusCode, String idProject){
        return
                given()
                        //.log().all() //выводит всю информацию о запросе, в штатном режиме лучше закоментировать
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz") //авторизация по логинуи паролю (этот код достал из Postman)
                        //.header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth") //в TestRail досточно авторизации по логину и паролю, хотя возможен доступ и по токену
                        .header("Content-Type", "application/json") //формат содержимого (отправка)
                        .header("Accept" , "application/json") //формат приема
                        .body(body) //тело запроса: перечисление отправляемых параметров и их значений
                .when() //собственно сам запрос на создание(изменение) проекта
                        .post(BASE_URL +"/api/v2/update_project/" + idProject)
                .then()
                        //.log().all() //выводит всю информацию об ответе, в штатном режиме лучше закоментировать
                        .statusCode(expectedStatusCode) //возвращает код ответа сервера
                        .extract().body().asString(); //возвращает тело ответа, если оно предусмотренно программистами
    }


    public int postProjectDelete(int expectedStatusCode, String idProject){
        return
                given()
                        //.log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .post(BASE_URL +"/api/v2/delete_project/" + idProject)
                .then()
                        //.log().all()
                        .statusCode(expectedStatusCode)
                        .extract().statusCode();
    }

    public String postProjectDeleteNegative(Integer expectedStatusCode, String idProject){
        return
                given()
                        //.log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .post(BASE_URL +"/api/v2/delete_project/" + idProject)
                .then()
                        //.log().all()
                        .statusCode(expectedStatusCode) //возвращает код ответа сервера
                        .extract().body().asString();
    }

    //Шаблон GET-запроса для возврата всех Project или по конкретному коду в TestRail, так как GET-запросы нимеют одинаковую структуру
    public String getProject(int expectedStatusCode, String url){
        return
                given()
                        //.log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .get(BASE_URL +"/api/v2/get_project/" + url)
                .then()
                        //.log().all()
                        .statusCode(expectedStatusCode)
                        .extract().body().asString();
    }

    public String getProjectAll(int expectedStatusCode){
        return
                given()
                        //.log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .get(BASE_URL +"/api/v2/get_projects")
                .then()
                        //.log().all()
                        .statusCode(expectedStatusCode)
                        .extract().body().asString();
    }
}
