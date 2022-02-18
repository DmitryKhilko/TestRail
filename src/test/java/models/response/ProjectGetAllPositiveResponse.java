package models.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectGetAllPositiveResponse {
    Integer limit;
    String size;
    List<ProjectResponse> projects;
}

