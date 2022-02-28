package models;

import com.github.javafaker.Faker;

import static tests.TestRunCRUDTest.*;

public class TestCaseFactory {

    static Faker faker = new Faker();

    public static TestCase get() {
        return TestCase.builder()
                .title(TESTCASE_TITLE)
                .section(SECTION_NAME)
                .template(TESTCASE_TEMPLATE)
                .type(TESTCASE_TYPE)
                .priority(TESTCASE_PRYORITY)
                .estimate(faker.number().digit())
                .references(faker.internet().url())
                .automationType(TESTCASE_AUTOMATIONTYPE)
                .preconditions(faker.artist().name())
                .steps(faker.animal().name())
                .expectedResult(faker.food().ingredient())
                .build();
    }
}

