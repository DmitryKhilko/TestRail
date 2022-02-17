package adapters;

import models.Project;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectGetPositiveResponse;
import models.response.ProjectPostPositiveResponse;

public class ProjectAdapter extends BaseAdapter {

    public ProjectPostPositiveResponse postProject(Project requestBody, int expectedStatusCode, String apiAction, String idProject){
        String response = postProject(gson.toJson(requestBody, Project.class), expectedStatusCode, apiAction, idProject);
        return gson.fromJson(response, ProjectPostPositiveResponse.class);
    }

    public ProjectGetPositiveResponse getOneProject(int expectedStatusCode, String idProject){
        String response = super.getProject(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectGetPositiveResponse.class);
    }

    public ProjectGetAllPositiveResponse getAllProject(int expectedStatusCode){
        String response = super.getProjectAll(expectedStatusCode);
        return gson.fromJson(response, ProjectGetAllPositiveResponse.class);
    }
}
