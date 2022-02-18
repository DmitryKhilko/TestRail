package models.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectAllPositiveResponse {
    Integer limit;
    String size;
    List<ProjectPositiveResponse> projects;
}

