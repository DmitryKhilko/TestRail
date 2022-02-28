package models;

import com.github.javafaker.Faker;

import static tests.TestRunCRUDTest.*;

public class TestRunFactory {

    static Faker faker = new Faker();

    public static TestRun get() {
        return TestRun.builder()
                .name(TESTRUN_NAME)
                .references(faker.internet().url())
                .milestone(TESTRUN_MILESTONE)
                .assignTo(TESTRUN_ASSIGN_TO)
                .description(faker.pokemon().name())
                .testcaseChoice(TESTRUN_TESTCASE_CHOICE)
                .build();
    }
}

