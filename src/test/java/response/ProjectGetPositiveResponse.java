package response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectGetPositiveResponse {
    Integer id;
    String name;
    String announcement;
    Boolean show_announcement;
    Integer suite_mode;
    Boolean is_completed;
    Integer default_role_id;
    String url;
}