package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {
    String title;
    String section;
    String template;
    String type;
    String priority;
    String estimate;
    String references;
    String automationType;
    String preconditions;
    String steps;
    String expectedResult;
}
