package response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectPostPositiveResponse {
    Integer id;
    String name;
    String announcement;
    Boolean show_announcement;
    Integer suite_mode;
}
