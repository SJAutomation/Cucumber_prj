package TestRunner2;

import org.junit.runner.RunWith;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(features="Features/Login.feature",
	           
					glue="stepDefinations",
							
						 
					dryRun = false, 
					monochrome = true,
					plugin =  {"pretty","html:test-output/CucumberReport.html"}
					
					)

public class Runner {
	
	
	

}

