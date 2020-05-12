package com.test.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        //To Run specific tests
        tags = "@filters",
        //To Run Smoke tests (All the tests)
        //tags = "@smoke",
        glue = {"classpath:stepdefs"},
        monochrome = true,
        plugin = {"json:target/cucumber-parallel/json/1.json",
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"}
)


public class TestRunner {

}