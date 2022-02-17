package adapters;

import models.Project;
import response.ProjectPositiveResponse;

public class ProjectAdapter extends BaseAdapter {

    public ProjectPositiveResponse postProject(Project requestBody, int expectedStatusCode, String apiAction, String idProject){
        String response = postProject(gson.toJson(requestBody, Project.class), expectedStatusCode, apiAction, idProject);
        return gson.fromJson(response, ProjectPositiveResponse.class);
    }
}
