package adapters;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;
import static pages.BasePage.BASE_URL;

public class BaseAdapter {
    Gson gson = new Gson(); //вынесли в BaseAdapter, так как данная библиотека будет использоваться во множестве адаптеров, например, ProjectAdapter. То есть, все адаптеры, которые наследуются от текущего класса, смогут использовать данную библиотеку
    //Шаблон POST-запроса для работы с Project в TestRail, так как POST-запросы на создание, изменение, удаление имеют одинаковую структуру
    public String postProject(String body, Integer expectedStatusCode, String apiAction, String idProject){
        return
                given()
                        .log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                        .body(body)
                .when()
                        .post(BASE_URL +"/api/v2/" + apiAction + "_project/" + idProject)
                .then()
                        .log().all()
                        .statusCode(expectedStatusCode)
                        .extract().body().asString();
    }

    public int postProjectDelete(Integer expectedStatusCode, String idProject){
        return
                given()
                        .log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .post(BASE_URL +"/api/v2/delete_project/" + idProject)
                .then()
                        //.log().all()
                        .statusCode(expectedStatusCode)
                        .extract()
                        .statusCode();
    }

    //Шаблон GET-запроса для возврата всех Project или по конкретному коду в TestRail, так как GET-запросы нимеют одинаковую структуру
    public String getProject(Integer expectedStatusCode, String url){
        return
                given()
                        .log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .get(BASE_URL +"/api/v2/get_project/" + url)
                .then()
                        .log().all()
                        .statusCode(expectedStatusCode)
                        .extract().body().asString();
    }

    public String getProjectAll(Integer expectedStatusCode){
        return
                given()
                        .log().all()
                        .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                        .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                        .header("Content-Type", "application/json")
                        .header("Accept" , "application/json")
                .when()
                        .get(BASE_URL +"/api/v2/get_projects")
                .then()
                        .log().all()
                        .statusCode(expectedStatusCode)
                        .extract().body().asString();
    }

}
