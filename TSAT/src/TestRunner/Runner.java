package TestRunner;



import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Feature-VISN_Scenarios",
        glue={"StepDefinition"},
      tags= {"@Supplier"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/SupplierFlowReport.html"}




)

public class Runner {
}
