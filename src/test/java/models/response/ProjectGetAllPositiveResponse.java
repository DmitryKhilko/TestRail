package models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectGetAllPositiveResponse {
    Integer limit;
    String size;
    GetAllProjects projects;
}

