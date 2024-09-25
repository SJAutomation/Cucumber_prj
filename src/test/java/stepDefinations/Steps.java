package stepDefinations;


import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;




public class Steps extends BaseClass {
	
	public static WebDriver driver;
	public LoginPage lp;


	//ChromeOptions opt=new ChromeOptions();
	//opt.addArguments("--remote-allow-origins=*");
	
	
	@BeforeClass(alwaysRun=true)// Add alwaysRun=true
	@Parameters("browser")
	public void setup(String br) throws IOException,NullPointerException
	{
		  
		
		String log4jConfPath = "./resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	//	logger.setLevel(Level.DEBUG);
		 //logger.debug(log4jConfPath);
		// logger.setLevel(Level.ALL);
		//System.setProperty("log4j.configurationFile", "log4j2.xml");
		/*Properties props = new Properties();
		props.load(new FileInputStream("log4j.properties"));
		props.setProperty("log4j.appender.File.File", "./logs/" + "automation.log");
		
		 // Logger logger=Logger.getLogger(this.getClass());  //Log4j2  */
	//  logger=Logg(this.getClass());

	    // PropertyConfigurator.configure("log4j2.properties");
		//DOMConfigurator.configure("log4.xml");
		
		//Load config.properties file
		configPropObj=new Properties();
		FileInputStream configfile=new FileInputStream(".//Configuration//config.properties");
		configPropObj.load(configfile);
		// end of loading config.properties file
		
		//String br=configPropObj.getProperty("browser"); 
		
		try
		{
		
		  if(br.equalsIgnoreCase("chrome")){
				//set path to chromedriver.exe
			//  WebDriverManager.chromedriver().setup();
			 System.setProperty("webdriver.chrome.driver","./Drivers//chromedriver.exe");
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
	
	@Given("Open chrome browser")
public void open_chrome_browser() throws Exception{
		
		//ChromeOptions opt=new ChromeOptions();
		//opt.addArguments("--remote-allow-origins=*");
		//WebDriverManager.chromedriver().setup();
		// System.setProperty("webdriver.chrome.driver","D:\\Downloads\\Chrome 120.0\\chromedriver-win32\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		//    options();
		
}

	

@When("User opens URL {string}")
public void user_opens_URL(String url) {
logger.info("************* Opening URL  *****************");
     lp=new LoginPage(driver);
driver.get(url);
 driver.manage().window().maximize();
}

@When("User enters Email as {string} and Password as {string}")
public void user_enters_Email_as_and_Password_as(String email, String password) throws Exception {
	logger.info("************* Providing user and password *****************");
	Thread.sleep(3000);
   
	lp.setUserName(email);
	lp.setPassword(password);
}

@When("Click on Login")
public void click_on_Login() throws InterruptedException {
	logger.info("************* click on login *****************");
	// lp=new LoginPage(driver);
	lp.clickLogin();
   Thread.sleep(5000);
}

@Then("Page Title should be {string}")
public void page_Title_should_be(String exptitle) throws InterruptedException {
    
	if(driver.getPageSource().contains("Login was unsuccessful."))
	{
		logger.info("************* Login failed *****************");
		driver.close();
		Assert.assertTrue(false);
	}
	else
	{
		logger.info("************* Login Passed *****************");
		//Assert.assertTrue(true);
		Assert.assertEquals(exptitle, driver.getTitle());
	}
	Thread.sleep(2000);
	
}



@When("User click on Log out link")
public void user_click_on_Log_out_link() throws InterruptedException {
	logger.info("************* clicking on logout *****************");
	// lp=new LoginPage(driver);
	lp.clickLogout();
    Thread.sleep(5000);
}


@Then("After logout Page Title should be {string}")
public void after_logout_page_title_should_be(String exptitle) throws InterruptedException {
	if(driver.getPageSource().contains("Login was unsuccessful."))
	{
		logger.info("************* Login failed *****************");
		driver.close();
		Assert.assertTrue(false);
	}
	else
	{
		logger.info("************* Login Passed *****************");
		//Assert.assertTrue(true);
		Assert.assertEquals(exptitle, driver.getTitle());
	}
	Thread.sleep(2000);
	
}


@Then("close browser")
public void close_browser() {
	logger.info("************* closing browser *****************");
   driver.quit();
}

}
