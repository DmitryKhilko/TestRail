package adapters;

import models.Project;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectGetPositiveResponse;
import models.response.ProjectPostPositiveResponse;

public class ProjectAdapter extends BaseAdapter {

    public ProjectPostPositiveResponse postAddProject(Project requestBody, Integer expectedStatusCode, String apiAction, String idProject){
        String response = postProject(gson.toJson(requestBody, Project.class), expectedStatusCode, apiAction, idProject);
        return gson.fromJson(response, ProjectPostPositiveResponse.class);
    }

    public ProjectPostPositiveResponse postUpdateProject(Project requestBody, Integer expectedStatusCode, String apiAction, String idProject){
        String response = postProject(gson.toJson(requestBody, Project.class), expectedStatusCode, apiAction, idProject);
        return gson.fromJson(response, ProjectPostPositiveResponse.class);
    }

    public ProjectPostPositiveResponse postOneProjectDelete(Integer expectedStatusCode, String idProject){
        String response = String.valueOf(super.postProjectDelete(expectedStatusCode, idProject));
        return gson.fromJson(response, ProjectPostPositiveResponse.class);
    }

    public ProjectGetPositiveResponse getOneProject(Integer expectedStatusCode, String idProject){
        String response = super.getProject(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectGetPositiveResponse.class);
    }

    public ProjectGetAllPositiveResponse getAllProject(Integer expectedStatusCode){
        String response = super.getProjectAll(expectedStatusCode);
        return gson.fromJson(response, ProjectGetAllPositiveResponse.class);
    }
}
