package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Regression","Sanity"})
	@Parameters("browser")
	public void setup(String br) throws IOException
	{
		
		logger = LogManager.getLogger(this.getClass());
		p= new Properties();
		FileReader file = new FileReader(".\\src\\test\\java\\resources\\config.properties");
		p.load(file);
		
		
		if(p.getProperty("Execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setBrowserName(br);
			cp.setPlatform(Platform.WIN11);			
			driver = new RemoteWebDriver(new URL("http://172.23.254.188:4444"), cp);
		
		}
		else {
		switch(br.toLowerCase()){
		case "chrome":driver= new ChromeDriver(); break;
		case "microsoftedge":driver= new EdgeDriver(); break;
		case "firefox" : driver= new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name"); return;
		}
		}
		
		driver.get(p.getProperty("appUrl"));
		logger.info("***** tutorialsninja Website opened *****");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		
	}
	
	@AfterClass(groups= {"Regression","Sanity"})
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomeString()
	{
		String str = RandomStringUtils.randomAlphabetic(5);
		return str;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphanumeric(8);
		return (str+"@");
	}

	public String captureScreen(String tname) {
		// TODO Auto-generated method stub
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}


}
