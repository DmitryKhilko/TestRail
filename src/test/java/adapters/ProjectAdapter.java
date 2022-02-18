package adapters;

import models.Project;
import models.response.ProjectGetAllPositiveResponse;
import models.response.ProjectResponse;
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

    public int postOneProjectDelete(Integer expectedStatusCode, String idProject){
        return super.postProjectDelete(expectedStatusCode, idProject);
    }

    public ProjectResponse getOneProject(Integer expectedStatusCode, String idProject){
        String response = super.getProject(expectedStatusCode, idProject);
        return gson.fromJson(response, ProjectResponse.class);
    }

    public ProjectGetAllPositiveResponse getAllProject(Integer expectedStatusCode){
        String response = super.getProjectAll(expectedStatusCode);
        return gson.fromJson(response, ProjectGetAllPositiveResponse.class);
    }
}
