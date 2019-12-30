package MyRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/Features", 
		glue = { "stepDefinitions" }, 
		plugin = { "pretty", "html:target/cucumber", "json:target/cucumber/cucumber.json"}, 
		monochrome = true, 
		strict = true,
		dryRun = false
		)

public class TestRunner {

}
