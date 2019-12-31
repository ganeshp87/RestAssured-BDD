package MyRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", 
		glue = { "stepDefinitions" }, 
		plugin = { "pretty", "html:target/cucumber", "json:target/cucumber-report/cucumber.json"}, 
		monochrome = true, 
		strict = true,
		dryRun = false
		)

public class TestRunner {

}
