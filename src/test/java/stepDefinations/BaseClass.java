package stepDefinations;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class BaseClass {
	

	public WebDriver driver;
	public LoginPage lp;
	//public AddcustomerPage addCust;
	//public SearchCustomerPage searchCust;
	public static Logger logger=Logger.getLogger("nopCommerce_Cucumber");
	public Properties configPropObj;
	
	//public Logger logger=(Logger) LoggerFactory.getLogger(this.getClass());  //Log4j2
	
	@BeforeClass(alwaysRun=true)// Add alwaysRun=true
	@Parameters("browser")
	public void setup(String br) throws IOException,NullPointerException
	{
		
		logger=Logger.getLogger("LoggerExample-Cucumber");
		String log4jConfPath = "./resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		//System.setProperty("log4j.configurationFile", "log4j2.xml");
		/*Properties props = new Properties();
		props.load(new FileInputStream("./log4j2.properties"));
		props.setProperty("log4j.appender.File.File", "./logs/" + "automation.log");
		*/
		 // Logger logger=Logger.getLogger(this.getClass());  //Log4j2  */
	//  logger=Logg(this.getClass());

	    // PropertyConfigurator.configure("log4j2.properties");
		//DOMConfigurator.configure("log4.xml");
		
		//Load config.properties file
		configPropObj=new Properties();
		FileInputStream configfile=new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
		configPropObj.load(configfile);
		// end of loading config.properties file
		//Object driver = null;
		
		//ChromeOptions opt=new ChromeOptions();
		//opt.addArguments("--remote-allow-origins=*");
		
		

		try
		{
		
		  if(br.equalsIgnoreCase("chrome")){
				//set path to chromedriver.exe
			 WebDriverManager.chromedriver().setup();
			 //System.setProperty("webdriver.chrome.driver","./Drivers//chromedriver.exe");
				//create chrome instance
				driver = new ChromeDriver();
			}

		  else if(br.equalsIgnoreCase("Edge")){
				//create firefox instance
				//System.setProperty("webdriver.edge.driver", "./Drivers//msedgedriver.exe");
			  //WebDriverManager.edgedriver().setup();	
			  driver = new EdgeDriver();
		  }
		 

		  else if(br.equalsIgnoreCase("Firefox")){
				//create firefox instance
				//System.setProperty("webdriver.gecko.driver", "./Drivers//geckodriver.exe");
				//WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		 
		  //Thread.sleep(5000);
		 // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
			 ((WebDriver) driver).manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			}
	
			catch(Exception e)
			{
				System.out.println("Exception is handled....");
			}

	}
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}
	
	
}
