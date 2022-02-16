package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIScriptTest {

    @Test
    public void getProject(){
        given()
                .log().all()
                .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                .header("Content-Type", "application/json")
                .header("Accept" , "application/json")
                .body("{}")
        .when()
                .post("https://hdn.testrail.io/index.php?/api/v2/get_project/17");
        //.then()
        //        .log().all();
        //        .statusCode(200);
    }

    @Test
    public void getProjects(){
        given()
                .log().all()
                .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                .header("Content-Type", "application/json")
                .header("Accept" , "application/json")
                .body("{}")
        .when()
                .post("https://hdn.testrail.io/index.php?/api/v2/get_projects");
        //.then()
        //        .log().all();
        //        .statusCode(200);
    }

    @Test
    public void createProject(){
        given()
                .log().all()
                .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                .header("Content-Type", "application/json")
                .header("Accept" , "application/json")
                .body("{\"name\": \"Proba2\", \"announcement\": \"This is the description for the project\", \"show_announcement\": true}")
        .when()
                .post("https://hdn.testrail.io/index.php?/api/v2/add_project/")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updateProject(){
        given()
                .log().all()
                .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                .header("Content-Type", "application/json")
                .header("Accept" , "application/json")
                .body("{\"name\": \"Proba2_update_proba\", \"announcement\": \"This is the description for the project_update_proba\"}")
        .when()
                .post("https://hdn.testrail.io/index.php?/api/v2/update_project/17")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteProject(){
        given()
                .log().all()
                .header("Authorization", "Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz")
                .header("NewToken", "czwdaFxSSHQwLvB6V6ei-phMdiar/73BUHkerBpth")
                .header("Content-Type", "application/json")
                .header("Accept" , "application/json")
                .body("{}")
        .when()
                .post("https://hdn.testrail.io/index.php?/api/v2/delete_project/19")
        .then()
                .log().all()
                .statusCode(200);
    }
}
