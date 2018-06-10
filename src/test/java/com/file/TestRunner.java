package com.file;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = "src/test/java/resources/Registration.feature", plugin = {"pretty", "html:target/cucumber"},
        dryRun = false,
        glue = "stepdefs")
public class TestRunner {

}
