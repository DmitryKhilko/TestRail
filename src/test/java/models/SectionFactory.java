package models;

import com.github.javafaker.Faker;

import static tests.TestRunCRUDTest.*;

public class SectionFactory {

    static Faker faker = new Faker();

    public static Section get() {
        return Section.builder()
                .name(SECTION_NAME)
                .description(faker.pokemon().name())
                .build();
    }
}

