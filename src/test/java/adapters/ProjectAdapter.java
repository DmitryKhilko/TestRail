package adapters;

import models.Project;

public class ProjectAdapter extends BaseAdapter {

    public String post(Project project, int statusCode, String apiAction, String idProject){
        return super.post(gson.toJson(project, Project.class), statusCode, apiAction, idProject);
    }
}
