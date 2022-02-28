package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRun {
    String name;
    String references;
    String milestone;
    String assignTo;
    String description;
    String testcaseChoice;
}
