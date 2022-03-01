package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Section {
    String name;
    String description;
}
